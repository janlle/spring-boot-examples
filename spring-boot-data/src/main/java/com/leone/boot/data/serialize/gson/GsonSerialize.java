package com.leone.boot.data.serialize.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;


import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public class GsonSerialize {

    private Gson gson = new Gson();

    
    public void singleSerialize() {
        long start = System.currentTimeMillis();

        // serialize simple pojo
        String userJson = gson.toJson(EntityFactory.getUser());

        long middle = System.currentTimeMillis();
        System.out.println("gson serialize time: " + (middle - start) + " millisecond!");

        User user = gson.fromJson(userJson, User.class);

        long end = System.currentTimeMillis();
        System.out.println("gson single deserialize time: " + (end - middle) + " millisecond!");
    }

    
    public void listSerialize() {
        long start = System.currentTimeMillis();

        String userListJson = gson.toJson(EntityFactory.getUsers(1000000));

        long middle = System.currentTimeMillis();
        System.out.println("gson serialize time: " + (middle - start) + " millisecond!");

        List<User> users = gson.fromJson(userListJson, new TypeToken<List<User>>() {
        }.getType());

        long end = System.currentTimeMillis();
        System.out.println("gson list deserialize time: " + (end - middle) + " millisecond!");
    }


}
