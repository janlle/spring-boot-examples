package com.leone.boot.shiro.controller;

import com.leone.boot.shiro.common.anno.AuthToken;
import com.leone.boot.shiro.config.TokenProperties;
import com.leone.boot.shiro.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "398122") String account,
                        @RequestParam(required = false, defaultValue = "459656") String password) {
        log.info("account: {} password: {}", account, password);
        String userId = "120040";
        String token = TokenUtil.generateToken(account, "1", tokenProperties.getSecret());
        stringRedisTemplate.opsForValue().set(tokenProperties.getRedisPrefix() + tokenProperties.getTokenPrefix() + userId, token, tokenProperties.getDuration(), TimeUnit.SECONDS);
        return "token: " + token;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeaderName());
        if (!StringUtils.isEmpty(token)) {
            String[] tokenArr = TokenUtil.validateToken(token, tokenProperties.getSecret());
            String userId = tokenArr[0];
            // 验证db用户

            //删除redis中token
            stringRedisTemplate.delete(tokenProperties.getRedisPrefix() + tokenProperties.getTokenPrefix() + userId);
        }
        return "logout";
    }

    @AuthToken
    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
