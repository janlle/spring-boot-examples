package com.andy.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Leone
 * @since 2018-07-16
 **/
@Configuration
@ConfigurationProperties(prefix = "model.shiro")
public class ShiroProperty {

    private boolean multiLogin = false;

    private int cacheDays = 14;

    private String redisPrefix = "default.prefix:";

    private String tokenPrefix = "default.auth.token:";

    private List<String> anonUrls;

    private List<String> coreUrls;

    private List<String> authUrls;

    public boolean isMultiLogin() {
        return multiLogin;
    }

    public void setMultiLogin(boolean multiLogin) {
        this.multiLogin = multiLogin;
    }

    public int getCacheDays() {
        return cacheDays;
    }

    public void setCacheDays(int cacheDays) {
        this.cacheDays = cacheDays;
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

    public List<String> getAnonUrls() {
        return anonUrls;
    }

    public void setAnonUrls(List<String> anonUrls) {
        this.anonUrls = anonUrls;
    }

    public List<String> getCoreUrls() {
        return coreUrls;
    }

    public void setCoreUrls(List<String> coreUrls) {
        this.coreUrls = coreUrls;
    }

    public List<String> getAuthUrls() {
        return authUrls;
    }

    public void setAuthUrls(List<String> authUrls) {
        this.authUrls = authUrls;
    }
}
