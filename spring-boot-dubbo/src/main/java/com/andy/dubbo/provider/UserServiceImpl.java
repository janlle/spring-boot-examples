package com.andy.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.andy.dubbo.common.UserService;
import com.andy.dubbo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产者
 *
 * @author Leone
 * @since 2018-06-03
 **/
@Slf4j
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(101L, "jack", "3456777533", "hello im jack", 15, new Date(), false));
        userList.add(new User(102L, "andy", "2357754332", "hello im andy", 19, new Date(), false));
        userList.add(new User(103L, "james", "145644333", "hi im james", 25, new Date(), false));
        userList.add(new User(104L, "tom", "2354557222", "hello im tom", 35, new Date(), false));
    }

    @Override
    public User findOne(Integer userId) {
        List<User> collect = userList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(collect)) {
            return collect.get(0);
        }
        return null;
    }

}
