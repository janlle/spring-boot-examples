package com.andy.data.json.jackson;

import com.andy.data.entity.User;
import com.andy.data.json.EntityFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

/**
 * @author: lyon
 * @since: 2018-05-11
 **/
public class JacksonSerialize {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ObjectMapper mapper = new ObjectMapper();

        //String json = mapper.writeValueAsString(user);
        //System.out.println(json);

        //User user = mapper.readValue(json, User.class);
        //System.out.println(user);


        String json = mapper.writeValueAsString(EntityFactory.getUsers(1000));
        List<User> users = mapper.readValue(json, new TypeReference<List<User>>() {
        });

        long end = System.currentTimeMillis();
        System.out.println("一共用了:" + (end - start) + "豪秒！");
    }

}
