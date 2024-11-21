package com.leone.boot.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.leone.boot.common.entity.Order;
import com.leone.boot.common.entity.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-13
 **/
public class EntityFactory {

    private static final Random random = new Random();

    public static List<User> userList = new LinkedList<>();

    public static List<Order> orderList = new LinkedList<>();

    static {
        for (long i = 0; i < 100; i++) {
            Date date = new Date(new Date().getTime() - (random.nextInt(1000000) + 1000000));
            userList.add(new User(i, RandomUtils.randomUsername(), IdUtil.nanoId(), RandomUtil.randomString(12), RandomUtils.random.nextInt(50) + 10, date, false));
            orderList.add(new Order(i, i, random.nextInt(1000) + 200, "Chicken and fish", IdUtil.nanoId(8), date, date, false));
            orderList.add(new Order(i, i, random.nextInt(100) + 200, "some apple and orange", IdUtil.nanoId(8), date, date, false));
        }
    }

    public static List<User> getUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        if (count < 1) {
            return Collections.emptyList();
        }
        for (long i = 0; i < count; i++) {
            userList.add(new User(i, RandomUtils.randomUsername(), IdUtil.nanoId(), RandomUtil.randomString(12), RandomUtils.random.nextInt(50) + 10, new Date(), false));
        }
        return userList;
    }

    public static User getUser() {
        return new User(RandomUtils.random.nextLong(), RandomUtils.randomUsername(), IdUtil.nanoId(), RandomUtil.randomString(12), RandomUtils.random.nextInt(50) + 10, new Date(), false);
    }

    public static User getUser(Long userId) {
        return userList.stream().filter(e -> e.getUserId().equals(userId)).toList().get(0);
    }

    public static void remove(Long userId) {
        userList.removeIf(next -> next.getUserId().equals(userId));
    }


    public static List<Order> getOrderList(Long userId) {
        return orderList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
    }


    public static Order getOrder(Long orderId) {
        return orderList.stream().filter(e -> e.getUserId().equals(orderId)).collect(Collectors.toList()).get(0);
    }

}