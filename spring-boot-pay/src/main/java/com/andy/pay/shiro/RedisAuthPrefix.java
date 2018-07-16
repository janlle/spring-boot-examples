package com.andy.pay.shiro;

public interface RedisAuthPrefix {

    String TOKEN_TO_USER_ID = "auth.token.id:";

    String USER_ID_TO_TOKEN = "auth.id.token:";

    String USER_ID_TO_ROLE = "auth.id.role:";

}
