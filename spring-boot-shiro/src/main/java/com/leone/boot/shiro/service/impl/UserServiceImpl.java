package com.leone.boot.shiro.service.impl;

import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.mapper.UserMapper;
import com.leone.boot.shiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-09
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * @param account
     * @return
     */
    @Override
    public User findAllPermissionByAccount(String account) {
        User user = userMapper.findAllPermissionByAccount(account);
        log.info("user: {}", user);
        return user;
    }

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    @Override
    public void logout(String account) {

    }

    @Override
    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

}