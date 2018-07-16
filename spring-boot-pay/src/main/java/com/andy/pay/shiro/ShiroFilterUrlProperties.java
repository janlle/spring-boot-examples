package com.andy.pay.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.shoro")
public class ShiroFilterUrlProperties {


    private List<String> anonUrls;

    private List<String> coreUrls;

    private List<String> authUrls;

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
