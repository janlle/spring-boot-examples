package com.leone.data.serializ.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leone.common.entity.User;
import com.leone.common.utils.EntityFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public class GsonSerialize {

    private Gson gson = new Gson();

    @Test
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

    @Test
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
