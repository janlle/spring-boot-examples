package com.andy.data.json.gson;

import com.alibaba.fastjson.JSON;
import com.andy.data.entity.User;
import com.andy.data.service.DataService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 23:17
 **/
public class GsonSeria {

    private static User user = new User(1033L, "james", "admin", new Date(), "andy@gmail.com", 10000.00, "token");


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Gson gson = new Gson();
        // 将Java对象序列化为Json字符串
        //String json = gson.toJson(user);
        //System.out.println(json);


        // 将Json字符串反序列化为Java对象
        //gson.fromJson(json, User.class);
        //System.out.println(user);



        String json = gson.toJson(DataService.getUsers());
        List<User> users = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
