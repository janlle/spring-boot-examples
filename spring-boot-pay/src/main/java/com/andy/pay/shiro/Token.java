package com.andy.pay.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class Token extends UsernamePasswordToken {

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