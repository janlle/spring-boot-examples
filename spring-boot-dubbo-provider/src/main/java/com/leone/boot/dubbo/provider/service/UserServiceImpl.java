package com.leone.boot.dubbo.provider.service;

import com.leone.boot.dubbo.api.UserService;
import com.leone.boot.dubbo.api.bean.User;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 生产者
 *
 * @author leone
 * @since 2018-06-03
 **/
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public User update(User user) {
        System.out.println("update");
        return user;
    }

    @Override
    public User save(User user) {
        System.out.println("save");
        return user;
    }

    @Override
    public User findOne(Long userId) {
        System.out.println("invoke findOne: " + userId);
        return new User(1L, "james", new Date());
    }

    @Override
    public List<User> list() {
        System.out.println("list");
        return Collections.singletonList(new User(1L, "james", new Date()));
    }

}
