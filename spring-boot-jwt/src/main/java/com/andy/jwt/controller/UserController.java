package com.andy.jwt.controller;

import com.andy.jwt.common.BaseResponse;
import com.andy.jwt.entity.User;
import com.andy.jwt.repository.UserRepository;
import com.andy.jwt.util.JwtTokenUtil;
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
 * @author Leone
 * @since 2018-04-19
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
        String token = JwtTokenUtil.createToken(ImmutableMap.of("email",user.getAccount(),"name",user.getUserId().toString(),"ts", Instant.now().getEpochSecond() + ""));
        user.setPassword(token);
        log.info("token={}", token);
        renewToken(token, user.getAccount());
        return BaseResponse.success(user);
    }


    //鉴权
    @GetMapping("/auth")
    public BaseResponse<Object> valid(String token) {
        Map<String, String> map = null;
        try {
            map = JwtTokenUtil.verifyToken(token);
        } catch (Exception e) {
            log.error("token validate fail!");
        }
        String account = map.get("account");

        Long expire = redisTemplate.getExpire(account);

        if (expire != null && expire > 0) {
            renewToken(token, account);
            User user = userRepository.findUserByAccount(account);
            user.setPassword(token);
            return BaseResponse.success("用户验证成功");
        } else {
            return BaseResponse.error("用户验证失败");
        }
    }

    @GetMapping("/user/{id}")
    public BaseResponse<User> user(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
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
        Map<String, String> map = JwtTokenUtil.verifyToken(token);
        redisTemplate.delete(map.get("email"));
    }


}
