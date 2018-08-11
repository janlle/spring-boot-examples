package com.andy.data.json.jackson;

import com.andy.data.entity.User;
import com.andy.data.service.JsonService;
import com.andy.data.service.RedisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11
 **/
public class JacksonSerialize {

    private static User user = new User(1, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ObjectMapper mapper = new ObjectMapper();

        //String json = mapper.writeValueAsString(user);
        //System.out.println(json);

        //User user = mapper.readValue(json, User.class);
        //System.out.println(user);



        String json = mapper.writeValueAsString(JsonService.getUsers());
        List<User> users = mapper.readValue(json, new TypeReference<List<User>>() {});

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
