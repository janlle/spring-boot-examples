package com.andy.data.config;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-08 15:44
 **/
public class RedisPrefix {

    public static String webUserCatch(String uuid) {
        return "app.user.web:" + uuid;
    }

    public static String cmsUserCatch(String uuid) {
        return "app.user.cms:" + uuid;
    }

}
