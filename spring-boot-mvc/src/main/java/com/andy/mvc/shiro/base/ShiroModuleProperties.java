package com.andy.mvc.shiro.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leone
 * @since 2018-05-17
 **/
@Configuration
@ConfigurationProperties(prefix = "module.shiro")
public class ShiroModuleProperties {

    private String tokenName = "auth.token:";

    private String tokenPrefix;

    private boolean multiLogin = false;

    private int cacheDays = 30;

    private List<String> anonUrls = new ArrayList<>();

    private List<String> corsUrls = new ArrayList<>();

    private List<String> authUrls = new ArrayList<>();

    public List<String> getAnonUrls() {
        if (anonUrls.isEmpty()) {
            anonUrls.add("/");
        }
        return anonUrls;
    }

    public void setAnonUrls(List<String> anonUrls) {
        this.anonUrls = anonUrls;
    }

    public List<String> getCorsUrls() {
        return corsUrls;
    }

    public void setCorsUrls(List<String> corsUrls) {
        this.corsUrls = corsUrls;
    }

    public List<String> getAuthUrls() {
        if (authUrls.isEmpty()) {
            authUrls.add("/api/**");
        }
        return authUrls;
    }

    public void setAuthUrls(List<String> authUrls) {
        this.authUrls = authUrls;
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
