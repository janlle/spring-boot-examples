package com.andy.mvc.shiro.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-17
 **/
@Configuration
@ConfigurationProperties(prefix = "module.shiro")
public class ShiroModuleProperties {

    private String tokenName = "auth.token:";

    private String tokenPrefix;

    private boolean multiLogin = false;

    private int cacheDays = 30;

    private List<String> anonUrl = new ArrayList<>();

    private List<String> corsUrl = new ArrayList<>();

    private List<String> authUrl = new ArrayList<>();

    public List<String> getAnonUrl() {
        if (anonUrl.isEmpty()) {
            anonUrl.add("/");
        }
        return anonUrl;
    }

    public void setAnonUrl(List<String> anonUrl) {
        this.anonUrl = anonUrl;
    }

    public List<String> getCorsUrl() {
        return corsUrl;
    }

    public void setCorsUrl(List<String> corsUrl) {
        this.corsUrl = corsUrl;
    }

    public List<String> getAuthUrl() {
        if (authUrl.isEmpty()) {
            authUrl.add("/api/**");
        }
        return authUrl;
    }

    public void setAuthUrl(List<String> authUrl) {
        this.authUrl = authUrl;
    }

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

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix + ":";
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
