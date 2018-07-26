package com.andy.aop.service.impl;

import com.andy.aop.entity.User;
import com.andy.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @author: Mr.lyon
 * @createBy: 2018-06-21 00:14
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User user(Long id) {
        log.info("目标类的user()方法");
        if (new Random().nextBoolean()) {
            throw new RuntimeException("UserService发生异常");
        }
        return new User(1L, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);
    }
}
