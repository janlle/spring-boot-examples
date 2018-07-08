package com.andy.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.andy.data.entity.User;
import com.andy.data.service.JsonService;
import com.andy.data.service.RedisService;

import java.util.Date;
import java.util.List;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 22:56
 **/
public class FastjsonSeria {

    private static User user = new User(1, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 将Java对象序列化为Json字符串
        //String json = JSON.toJSONString(user);
        //System.out.println(json);

        // 将Json字符串反序列化为Java对象
        //User user = JSON.parseObject(json, User.class);
        //System.out.println(user);

        String json = JSON.toJSONString(JsonService.getUsers());
        List<User> userList = JSON.parseObject(json, new TypeReference<List<User>>(){}.getType());

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
