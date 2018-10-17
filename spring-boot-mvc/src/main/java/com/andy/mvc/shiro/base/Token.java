package com.andy.mvc.shiro.base;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Leone
 **/
public class Token implements AuthenticationToken {

    private String token;

    public Token(String token) {
        this.token = token;
    }

    String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return getToken();
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }

}
