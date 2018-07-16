package com.andy.pay.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class Token implements AuthenticationToken {

    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public Object getPrincipal() {
        return this.getToken();
    }

    public Object getCredentials() {
        return this.getToken();
    }
}