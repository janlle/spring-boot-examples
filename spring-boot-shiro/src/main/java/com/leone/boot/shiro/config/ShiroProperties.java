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
@ConfigurationProperties(prefix = "app.shiro")
public class ShiroProperties {

    // token缓存时长单位分钟
    private Integer cacheTime = 4320;

    // redis中token存贮的前缀
    private String redisPrefix = "shiro:token";

    // token的前缀
    private String tokenPrefix = "token";

    // 匿名访问的url
    private Set<String> anonUrls = Collections.emptySet();

    // 需要人证访问的url
    private Set<String> authUrls = Collections.emptySet();

    public Integer getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(Integer cacheTime) {
        this.cacheTime = cacheTime;
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

    public Set<String> getAnonUrls() {
        return anonUrls;
    }

    public void setAnonUrls(Set<String> anonUrls) {
        this.anonUrls = anonUrls;
    }

    public Set<String> getAuthUrls() {
        return authUrls;
    }

    public void setAuthUrls(Set<String> authUrls) {
        this.authUrls = authUrls;
    }
}
