package com.andy.data.config;

/**
 * @author: lyon
 * @since: 2018-07-08 15:44
 **/
public class RedisPrefix {

    public static String userCatch(String uuid) {
        return "app.user:" + uuid;
    }

}
