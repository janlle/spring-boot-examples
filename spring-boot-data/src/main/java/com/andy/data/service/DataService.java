package com.andy.data.service;

import com.andy.data.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 19:41
 **/
public class DataService {

    private static List<User> users = new ArrayList<>();

    private static User user = new User(10009L, "james", "admin", new Date(), "andy@gmail.com", 10000 + 0.1, "token:84dsajf823djsi");

    private static String jsonUserData = "{\"username\":\"james\",\"password\":\"admin\",\"email\":\"andy@163.com\",\"token\":\"token:12idkail8lld\",\"salary\":10004,\"birthday\":\"2018-04-02\"}";

    static {
        for (long i = 0; i < 10000; i++) {
            users.add(new User(i, "james"+i, "admin"+i, new Date(), "andy@gmail.com", 10000 + 0.1, "token:84dsajf823djsi"));
        }
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getUser() {
        return user;
    }

    public static String getJsonUserData() {
        return jsonUserData;
    }

}
