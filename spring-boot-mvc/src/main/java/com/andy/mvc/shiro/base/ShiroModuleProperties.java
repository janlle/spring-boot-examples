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
@ConfigurationProperties(prefix = "luwei.module.shiro")
public class ShiroModuleProperties {

    private String token_to_user_id = "auth.token.id:";

    private String user_id_to_token = "auth.id.token:";

    private String user_id_to_role = "auth.id.role:";

    private boolean multiLogin = false;

    private int cacheDays = 30;

    private String prefix;

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix+":";
    }

    public String getToken_to_user_id() {
        return token_to_user_id;
    }

    public void setToken_to_user_id(String token_to_user_id) {
        this.token_to_user_id = token_to_user_id;
    }

    public String getUser_id_to_token() {
        return user_id_to_token;
    }

    public void setUser_id_to_token(String user_id_to_token) {
        this.user_id_to_token = user_id_to_token;
    }

    public String getUser_id_to_role() {
        return user_id_to_role;
    }

    public void setUser_id_to_role(String user_id_to_role) {
        this.user_id_to_role = user_id_to_role;
    }
}
