package com.andy.shiro.service.impl;

import com.andy.shiro.entity.rbac.User;
import com.andy.shiro.mapper.UserMapper;
import com.andy.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        log.info("UserServiceImpl.findByUsername()");
        return userMapper.findByUsername(username);
    }


}