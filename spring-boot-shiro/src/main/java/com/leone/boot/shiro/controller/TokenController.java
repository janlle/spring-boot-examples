package com.leone.boot.shiro.controller;

import com.leone.boot.shiro.common.Result;
import com.leone.boot.shiro.common.anno.AuthToken;
import com.leone.boot.shiro.config.TokenProperties;
import com.leone.boot.shiro.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2020-05-26
 **/
@RestController
@RequestMapping("/api/token")
public class TokenController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String userId = "120040";

    @PostMapping("/login")
    public Result login(@RequestParam(required = false, defaultValue = "398122") String account,
                        @RequestParam(required = false, defaultValue = "459656") String password) {
        log.info("account: {} password: {}", account, password);
        String token = TokenUtil.generateToken(userId, "1", tokenProperties.getSecret());
        stringRedisTemplate.opsForValue().set(tokenProperties.getRedisPrefix() + ":" + tokenProperties.getTokenPrefix() + ":" + userId, token, tokenProperties.getDuration(), TimeUnit.SECONDS);
        return Result.success(token);
    }

    @PostMapping("/logout")
    public Result<Object> logout(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeaderName());
        if (!StringUtils.isEmpty(token)) {
            String[] tokenArr = TokenUtil.validateToken(token, tokenProperties.getSecret());
            String userId = tokenArr[0];
            // 验证db用户

            //删除redis中token
            stringRedisTemplate.delete(tokenProperties.getRedisPrefix() + ":" + tokenProperties.getTokenPrefix() + ":" + userId);
        }
        return Result.success(null);
    }

    @AuthToken
    @GetMapping("/test")
    public Result<Object> test() {
        return Result.success(null);
    }

}
