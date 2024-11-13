package com.leone.boot.jwt.controller;

import com.leone.boot.common.Result;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.exception.ExceptionEnum;
import com.leone.boot.jwt.util.JwtTokenUtil;
import com.leone.boot.jwt.repository.UserRepository;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author leone
 * @since 2018-04-19
 **/
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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
            return Result.error(ExceptionEnum.AUTH_TOKEN);
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
        // Assert.notNull(map);
        redisTemplate.delete(map.get("email"));
    }


}
