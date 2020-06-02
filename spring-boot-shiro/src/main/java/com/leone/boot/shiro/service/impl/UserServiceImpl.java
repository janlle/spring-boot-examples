package com.leone.boot.shiro.service.impl;

import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.mapper.UserMapper;
import com.leone.boot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-09
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

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