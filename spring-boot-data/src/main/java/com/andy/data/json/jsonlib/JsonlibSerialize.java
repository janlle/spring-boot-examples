package com.andy.data.json.jsonlib;

import com.andy.data.entity.User;
import com.andy.data.util.EntityFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11
 **/
public class JsonlibSerialize {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        JSONObject userJson = JSONObject.fromObject(EntityFactory.getUsers(1).get(0));
        System.out.println(userJson.toString());

        User user = (User)JSONObject.toBean(userJson, User.class);
        System.out.println(user);


        JSONArray json = JSONArray.fromObject(EntityFactory.getUsers(1000));
        List<User> users = JSONArray.toList(json, User.class);

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
