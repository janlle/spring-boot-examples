package com.andy.mvc.shiro;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
    prefix = "module.shiro"
)
@Configuration
public class ShiroFilterUrlProperties {
    private List<String> anonUrl;
    private List<String> corsUrl;
    private List<String> authUrl;

    public ShiroFilterUrlProperties() {
    }

    public List<String> getAnonUrl() {
        return this.anonUrl;
    }

    public void setAnonUrl(List<String> anonUrl) {
        this.anonUrl = anonUrl;
    }

    public List<String> getCorsUrl() {
        return this.corsUrl;
    }

    public void setCorsUrl(List<String> corsUrl) {
        this.corsUrl = corsUrl;
    }

    public List<String> getAuthUrl() {
        return this.authUrl;
    }

    public void setAuthUrl(List<String> authUrl) {
        this.authUrl = authUrl;
    }
}
