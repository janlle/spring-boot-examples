package com.leone.boot.data.serialize.jsonlib;

import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public class JsonlibSerialize {

    
    public void singleSerialize() {
        long start = System.currentTimeMillis();

        JSONObject userJson = JSONObject.fromObject(EntityFactory.getUser());

        long middle = System.currentTimeMillis();
        System.out.println("jsonlib single serialize time: " + (middle - start) + " millisecond!");

        User user = (User) JSONObject.toBean(userJson, User.class);

        long end = System.currentTimeMillis();
        System.out.println("jsonlib single deserialize time: " + (end - middle) + " millisecond!");
    }

    
    public void listSerialize() {
        long start = System.currentTimeMillis();

        JSONArray userJson = JSONArray.fromObject(EntityFactory.getUsers(1000000));

        long middle = System.currentTimeMillis();
        System.out.println("jsonlib list serialize time: " + (middle - start) + " millisecond!");

        List userList = JSONArray.toList(userJson, User.class);

        long end = System.currentTimeMillis();
        System.out.println("jsonlib list deserialize time: " + (end - middle) + " millisecond!");
    }

}
