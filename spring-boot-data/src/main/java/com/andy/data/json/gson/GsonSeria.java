package com.andy.data.json.gson;

import com.andy.data.entity.User;
import com.andy.data.service.JsonService;
import com.andy.data.service.RedisService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11
 **/
public class GsonSeria {

    private static User user = new User(1, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Gson gson = new Gson();
        // 将Java对象序列化为Json字符串
        //String json = gson.toJson(user);
        //System.out.println(json);


        // 将Json字符串反序列化为Java对象
        //gson.fromJson(json, User.class);
        //System.out.println(user);



        String json = gson.toJson(JsonService.getUsers());
        List<User> users = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
