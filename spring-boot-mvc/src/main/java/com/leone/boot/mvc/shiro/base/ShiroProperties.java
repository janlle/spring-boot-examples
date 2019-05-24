package com.leone.boot.mvc.shiro.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author leone
 **/
@Configuration
@ConfigurationProperties(prefix = "module.shiro")
public class ShiroProperties {

    private String tokenPrefix = "shiro.auth.token:";

    // 单位分钟
    private Integer cacheTime = 10080;

    private String tokenSecret = "@J(#_=*!A";

    private Set<String> anonUrls = Collections.emptySet();

    private Set<String> authUrls = Collections.emptySet();

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(Integer cacheTime) {
        this.cacheTime = cacheTime;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
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
