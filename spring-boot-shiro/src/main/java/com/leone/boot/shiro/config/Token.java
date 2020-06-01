package com.leone.boot.shiro.config;

import com.leone.boot.shiro.utils.TokenUtil;

import java.io.Serializable;

/**
 * Token: userId.role.seed
 *
 * @author leone
 **/
public class Token implements Serializable {

    private String token;

    private String userId;

    private String role;

    public Token(String token, String seed) {
        this.token = token;
        if (token != null) {
            String[] res = TokenUtil.split(TokenUtil.encode(token, seed));
            if (res != null && res.length == 3) {
                userId = res[0];
                role = res[1];
            }
        }
    }

    public void decode(String seed) {

    }

    String getToken() {
        return token;
    }


    public String getUserId() {
        return userId;
    }

    public String getRule() {
        return role;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", rule='" + role + '\'' +
                '}';
    }
}
