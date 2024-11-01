package com.leone.boot.data.serialize.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;


import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public class FastJsonSerialize {

    
    public void singleSerialize() {
        long start = System.currentTimeMillis();

        // 将Java对象序列化为Json字符串
        String userJson = JSON.toJSONString(EntityFactory.getUser());
        System.out.println(userJson);

        long middle = System.currentTimeMillis();
        System.out.println("fastJson serialize time: " + (middle - start) + " millisecond!");

        // 将Json字符串反序列化为Java对象
        User user = JSON.parseObject(userJson, User.class);

        long end = System.currentTimeMillis();
        System.out.println("fastJson deserialize time: " + (end - middle) + " millisecond!");
    }

    
    public void listSerialize() {

        long start = System.currentTimeMillis();

        String userListJson = JSON.toJSONString(EntityFactory.getUsers(1000000));

        long middle = System.currentTimeMillis();
        System.out.println("fastJson serialize time: " + (middle - start) + " millisecond!");

        List<User> userList = JSON.parseObject(userListJson, new TypeReference<List<User>>() {
        }.getType());

        long end = System.currentTimeMillis();
        System.out.println("fastJson deserialize time: " + (end - middle) + " millisecond!");
    }


}
