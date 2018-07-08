package com.andy.data.json.jsonlib;

import com.andy.data.entity.User;
import com.andy.data.service.DataService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 23:17
 **/
public class JsonlibSeria {

    private static User user = new User(1, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        //JSONObject json = JSONObject.fromObject(user);
        //System.out.println(json.toString());


        //User user = (User)JSONObject.toBean(json, User.class);
        //System.out.println(user);


        JSONArray json = JSONArray.fromObject(DataService.getUsers());

        List<User> users = JSONArray.toList(json, User.class);


        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
