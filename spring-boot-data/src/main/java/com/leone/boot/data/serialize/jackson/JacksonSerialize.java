package com.leone.boot.data.serialize.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public class JacksonSerialize {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void singleSerialize() throws IOException {
        long start = System.currentTimeMillis();
        // simple pojo
        String userJson = mapper.writeValueAsString(EntityFactory.getUser());

        long middle = System.currentTimeMillis();
        System.out.println("jackson serialize time: " + (middle - start) + " millisecond!");

        User user = mapper.readValue(userJson, User.class);
        long end = System.currentTimeMillis();
        System.out.println("jackson single deserialize time: " + (end - middle) + " millisecond!");
    }

    @Test
    public void listSerialize() throws IOException {
        long start = System.currentTimeMillis();
        String listJson = mapper.writeValueAsString(EntityFactory.getUsers(1000000));

        long middle = System.currentTimeMillis();
        System.out.println("jackson serialize time: " + (middle - start) + " millisecond!");

        List<User> users = mapper.readValue(listJson, new TypeReference<List<User>>() {
        });
        long end = System.currentTimeMillis();
        System.out.println("jackson list deserialize time: " + (end - middle) + " millisecond!");
    }

}
