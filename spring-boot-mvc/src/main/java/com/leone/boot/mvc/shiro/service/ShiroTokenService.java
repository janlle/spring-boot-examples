package com.leone.boot.mvc.shiro.service;

import com.leone.boot.mvc.shiro.ShiroProperties;
import com.leone.boot.mvc.shiro.TokenUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *
 * @author leone
 **/
//@Service
public class ShiroTokenService {

    private final Logger logger = LoggerFactory.getLogger(ShiroTokenService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ShiroProperties shiroProperties;

    /**
     * 登录
     *
     * @param userId
     * @return
     */
    public String login(String userId) {
        return updateToken(userId, null);
    }

    /**
     * 登录
     *
     * @param userId
     * @param role
     * @return
     */
    public String login(String userId, String role) {
        return updateToken(userId, role);
    }

    /**
     * @param userId
     * @param role
     * @return
     */
    private String updateToken(String userId, String role) {
        String salt = UUID.randomUUID().toString().substring(0, 4);
        String tokenPrefix = shiroProperties.getTokenPrefix();
        int catchTime = shiroProperties.getCacheTime();
        String token;
        if (Objects.isNull(role) && "".equals(role)) {
            token = TokenUtil.encode(userId + "." + role + "." + salt, shiroProperties.getTokenSecret());
        } else {
            token = TokenUtil.encode(userId + "." + "-1" + "." + salt, shiroProperties.getTokenSecret());
        }
        logger.info("setToken userId: {} -- token: {}", userId, token);
        this.stringRedisTemplate.opsForValue().set(tokenPrefix + userId, token, catchTime, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 登出
     *
     * @param userId
     */
    public String logout(String userId) {
        stringRedisTemplate.delete(shiroProperties.getTokenPrefix() + userId);
        return userId;
    }

}
