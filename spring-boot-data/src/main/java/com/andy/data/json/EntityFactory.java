package com.andy.data.json;

import com.andy.data.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @author: lyon
 * @since: 2018-08-11
 **/
@Slf4j
public class EntityFactory {

    private static User user = new User(1, "jack", "adI#*l-ia#", new Date(), 10000 + 0.1, new Date(), false);

    private static String jsonUserData = "{\"username\":\"james\",\"password\":\"admin\",\"email\":\"andy@163.com\",\"token\":\"token:12idkail8lld\",\"salary\":10004,\"birthday\":\"2018-04-02\"}";


    public static List<User> getUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User(i, "jack", "adI#*" + i + "i-a#", new Date(), 1 * 1000 + 0.1, new Date(), false);
            userList.add(user);
        }
        return userList;
    }

    public static User getUser() {
        return user;
    }

    public static String getJsonUser() {
        return jsonUserData;
    }

}
