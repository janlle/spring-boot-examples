package com.andy.aop.service.impl;

import com.andy.aop.entity.User;
import com.andy.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-21 00:14
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User user(Long id) {
        log.info("目标类的user()方法");
        return new User(id, "username", "user@126.com", 12000.0, new Date());
    }
}
