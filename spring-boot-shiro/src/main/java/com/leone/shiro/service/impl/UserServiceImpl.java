package com.leone.shiro.service.impl;

import com.leone.shiro.entity.rbac.User;
import com.leone.shiro.mapper.UserMapper;
import com.leone.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p> 
 *
 * @author Leone
 * @since 2018-11-09
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByAccount(String account) {
//        User user = userMapper.getByAccount(account);
        User user = new User();
        log.info("user:{}", user);
        return user;
    }


}