package com.andy.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.andy.data.entity.User;
import com.andy.data.util.EntityFactory;

import java.util.List;

/**
 * @author Leone
 * @since: 2018-05-11
 **/
public class FastJsonSerialize {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 将Java对象序列化为Json字符串
        String userJson = JSON.toJSONString(EntityFactory.getUsers(1).get(0));
        System.out.println(userJson);

        // 将Json字符串反序列化为Java对象
        User user = JSON.parseObject(userJson, User.class);
        System.out.println(user);


        String listJson = JSON.toJSONString(EntityFactory.getUsers(1000));
        List<User> userList = JSON.parseObject(listJson, new TypeReference<List<User>>() {
        }.getType());

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
