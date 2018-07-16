package com.andy.pay.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app.shiro")
@Configuration
public class ShiroProperties {

    private boolean multiLogin = false;
    private int cacheDays = 30;
    private String prefix;

    public ShiroProperties() {
    }

    public boolean isMultiLogin() {
        return this.multiLogin;
    }

    public void setMultiLogin(boolean multiLogin) {
        this.multiLogin = multiLogin;
    }

    public int getCacheDays() {
        return this.cacheDays;
    }

    public void setCacheDays(int cacheDays) {
        this.cacheDays = cacheDays;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix + ":";
    }
}
