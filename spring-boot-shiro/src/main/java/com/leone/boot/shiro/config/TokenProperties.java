package com.leone.boot.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

/**
 * @author leone
 * @since 2018-07-16
 **/
@Configuration
@ConfigurationProperties(prefix = "app.token")
public class TokenProperties {

    public TokenProperties() {
    }

    public TokenProperties(Integer duration, String redisPrefix, String tokenPrefix, String headerName, String secret) {
        this.duration = duration;
        this.redisPrefix = redisPrefix;
        this.tokenPrefix = tokenPrefix;
        this.headerName = headerName;
        this.secret = secret;
    }

    // token 缓存时长单位分钟
    private Integer duration;

    // redis 中 token 存贮的前缀
    private String redisPrefix;

    // token 的前缀
    private String tokenPrefix;

    // 前端请求的 header 名称
    private String headerName;

    // 秘钥
    private String secret;


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRedisPrefix() {
        return redisPrefix;
    }

    public void setRedisPrefix(String redisPrefix) {
        this.redisPrefix = redisPrefix;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
