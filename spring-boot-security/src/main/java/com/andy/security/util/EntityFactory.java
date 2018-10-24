package com.andy.security.util;

import com.andy.security.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-08-11
 **/
@Slf4j
public class EntityFactory {

    private static final String jsonUserData = "{\"username\":\"james\",\"password\":\"admin\",\"email\":\"andy@163.com\",\"token\":\"token:12idkail8lld\",\"salary\":10004,\"birthday\":\"2018-04-02\"}";

    /**
     * 获取object数据格式数据
     *
     * @param count
     * @return
     */
    public static List<User> getUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            User user = new User(Long.parseLong(1 + RandomUtil.getNum(8)), "jack", RandomUtil.getStr(12), "hello my friend i am a boy " + RandomUtil.getStr(6), 18, new Date(), false);
            userList.add(user);
        }
        return userList;
    }

    public static User getUser() {
        return new User(Long.parseLong(1 + RandomUtil.getNum(8)), "andy", RandomUtil.getStr(12), "hello my friend i am a boy " + RandomUtil.getStr(6), 18, new Date(), false);
    }

    /**
     * 获取json数据格式数据
     *
     * @return
     */
    public static String getJsonUser() {
        return jsonUserData;
    }

}
