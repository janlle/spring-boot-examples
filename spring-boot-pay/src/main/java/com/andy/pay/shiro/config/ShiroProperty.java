package com.andy.pay.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-16 11:59
 **/
@Configuration
@ConfigurationProperties(prefix = "model.shiro")
public class ShiroProperty {

    private boolean multiLogin = false;

    private int cacheDays = 30;

    private String prefix;

    private String tokenUserId = "auth.token.id:";

    private String userIdToken = "auth.id.token:";

    private String userIdRole = "auth.id.role:";

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix + ":";
    }

    public String getTokenUserId() {
        return tokenUserId;
    }

    public void setTokenUserId(String tokenUserId) {
        this.tokenUserId = tokenUserId;
    }

    public String getUserIdToken() {
        return userIdToken;
    }

    public void setUserIdToken(String userIdToken) {
        this.userIdToken = userIdToken;
    }

    public String getUserIdRole() {
        return userIdRole;
    }

    public void setUserIdRole(String userIdRole) {
        this.userIdRole = userIdRole;
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
