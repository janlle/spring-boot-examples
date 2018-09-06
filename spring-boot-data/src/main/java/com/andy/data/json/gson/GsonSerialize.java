package com.andy.data.json.gson;

import com.andy.data.entity.User;
import com.andy.data.util.EntityFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author Leone
 * @since 2018-05-11
 **/
public class GsonSerialize {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Gson gson = new Gson();

        // serialize simple pojo
        String userJson = gson.toJson(EntityFactory.getUsers(1).get(0));
        System.out.println(userJson);

        User user = gson.fromJson(userJson, User.class);
        System.out.println(user);


        String listJson = gson.toJson(EntityFactory.getUsers(1000));
        List<User> users = gson.fromJson(listJson, new TypeToken<List<User>>() {
        }.getType());

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
