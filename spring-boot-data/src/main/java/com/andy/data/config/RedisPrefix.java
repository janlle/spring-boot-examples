package com.andy.data.config;

/**
 * @author: lyon
 * @createBy: 2018-07-08 15:44
 **/
public class RedisPrefix {

    public static String webUserCatch(String uuid) {
        return "app.web.token:" + uuid;
    }

    public static String cmsUserCatch(String uuid) {
        return "app.cms.token:" + uuid;
    }

}
