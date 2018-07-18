package com.andy.mvc.shiro;

public interface RedisAuthPrefix {
    String TOKEN_TO_USERID = "auth.token.id:";
    String USERID_TO_TOKEN = "auth.id.token:";
    String USERID_TO_ROLE = "auth.id.role:";
}