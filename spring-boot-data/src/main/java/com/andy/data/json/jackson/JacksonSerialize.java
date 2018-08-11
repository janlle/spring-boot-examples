package com.andy.data.json.jackson;

import com.andy.data.entity.User;
import com.andy.data.json.EntityFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11
 **/
public class JacksonSerialize {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        long start = System.currentTimeMillis();

        // simple pojo
        String userJson = mapper.writeValueAsString(EntityFactory.getUsers(1).get(0));
        System.out.println(userJson);

        User user = mapper.readValue(userJson, User.class);
        System.out.println(user);


        // list
        String listJson = mapper.writeValueAsString(EntityFactory.getUsers(1000));
        List<User> users = mapper.readValue(listJson, new TypeReference<List<User>>() {
        });

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
