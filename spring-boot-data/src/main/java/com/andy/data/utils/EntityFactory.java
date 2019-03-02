package com.andy.data.utils;

import com.andy.data.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class EntityFactory {

    private static Random random = new Random();

    public static List<User> userList = new LinkedList<>();


    static {
        for (long i = 0; i < 100; i++) {
            Date date = new Date(new Date().getTime() - (random.nextInt(1000000) + 1000000));
            userList.add(new User(i, RandomValue.randomUsername(), RandomValue.randomStr(16), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 10, date, false));
        }
    }

    public static final String JSON_USER = "{\"username\":\"james\",\"password\":\"admin\",\"email\":\"andy@163.com\",\"token\":\"token:12idkail8lld\",\"salary\":10004,\"birthday\":\"2018-04-02\"}";

    /**
     * 获取object数据格式数据
     *
     * @param count
     * @return
     */
    public static List<User> getUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        if (count < 1) {
            return Collections.emptyList();
        }
        for (long i = 0; i < count; i++) {
            userList.add(new User(i, RandomValue.randomUsername(), RandomValue.randomUUID(), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 12, RandomValue.randomDate(), random.nextBoolean()));
        }
        return userList;
    }


    /**
     * @return
     */
    public static User getUser() {
        return new User(RandomValue.random.nextLong(), RandomValue.randomUsername(), RandomValue.randomUUID(), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 12, RandomValue.randomDate(), random.nextBoolean());
    }

    /**
     * @param userId
     * @return
     */
    public static User getUser(Long userId) {
        return userList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
    }

    /**
     * @param userId
     */
    public static void remove(Long userId) {
        userList.removeIf(next -> next.getUserId().equals(userId));
    }

}