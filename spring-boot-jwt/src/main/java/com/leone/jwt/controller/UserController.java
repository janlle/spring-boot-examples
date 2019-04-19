package com.leone.jwt.controller;

import com.leone.common.Result;
import com.leone.common.entity.User;
import com.leone.jwt.repository.UserRepository;
import com.leone.jwt.util.JwtTokenUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Leone
 * @since 2018-04-19
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;


    // 用户登录功能
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) throws Exception {
        String token = JwtTokenUtil.createToken(ImmutableMap.of("account", user.getAccount(), "userId", user.getUserId().toString(), "ts", String.valueOf(System.currentTimeMillis())));
        user.setPassword(token);
        log.info("token: {}", token);
        renewToken(token, user.getAccount());
        return Result.success(user);
    }


    // 鉴权
    @GetMapping("/auth")
    public Result<Object> valid(String token) {
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
            return Result.success("用户验证成功");
        } else {
            return Result.error("用户验证失败");
        }
    }

    @GetMapping("/user/{account}")
    public Result<User> user(@PathVariable Long account) {
        User user = userRepository.findById(account).orElse(null);
        return Result.success(user);
    }

    @PostMapping("/logout")
    public Result<String> logout(String account) {
        redisTemplate.delete(account);
        return Result.success("登出成功");
    }

    // 更新token
    public void renewToken(String token, String email) {
        redisTemplate.opsForValue().set(email, token, 30, TimeUnit.MINUTES);
    }

    // 删除token
    public void invalidate(String token) {
        Map<String, String> map = JwtTokenUtil.verifyToken(token);
        Assert.notNull(map);
        redisTemplate.delete(map.get("email"));
    }


}
