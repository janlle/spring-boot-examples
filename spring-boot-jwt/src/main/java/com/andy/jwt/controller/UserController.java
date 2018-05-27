package com.andy.jwt.controller;

import com.andy.jwt.common.BaseResponse;
import com.andy.jwt.entity.User;
import com.andy.jwt.repository.UserRepository;
import com.andy.jwt.util.JwtToken;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19 19:32
 **/
@Slf4j
@RestController
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserRepository userRepository;


    //用户登录功能
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody User user) throws Exception {
        String token = JwtToken.getToken(ImmutableMap.of("email",user.getEmail(),"name",user.getUsername(),"ts", Instant.now().getEpochSecond() + ""));
        user.setToken(token);
        log.info("token={}", token);
        renewToken(token, user.getEmail());
        return BaseResponse.success(user);
    }


    //鉴权
    @GetMapping("/auth")
    public BaseResponse<Object> valid(String token) {
        Map<String, String> map = null;
        try {
            map = JwtToken.verifyToken(token);
        } catch (Exception e) {
            log.error("token validate fail!");
        }
        String email = map.get("email");

        Long expild = redisTemplate.getExpire(email);

        if (expild > 0) {
            renewToken(token, email);
            User user = userRepository.findUserByEmail(email);
            user.setToken(token);
            return BaseResponse.success("用户验证成功");
        } else {
            return BaseResponse.error("用户验证失败");
        }
    }

    @GetMapping("/user/{id}")
    public BaseResponse<User> user(@PathVariable Long id) {
        User user = userRepository.findUserById(id);
        return BaseResponse.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout(String email) {
        redisTemplate.delete(email);
        return BaseResponse.success("登出成功");
    }


    public void renewToken(String token, String email) {
        redisTemplate.opsForValue().set(email, token, 30, TimeUnit.MINUTES);
    }


    public void invalidate(String token) {
        Map<String, String> map = JwtToken.verifyToken(token);
        redisTemplate.delete(map.get("email"));
    }


}
