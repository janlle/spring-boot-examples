package com.leone.boot.shiro.service.impl;

import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.mapper.SysUserMapper;
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
    private SysUserMapper userMapper;

    @Override
    public SysUser findAllPermissionByAccount(String account) {
        SysUser user = userMapper.findAllPermissionByAccount(account);
        log.info("findAllPermissionByAccount: {}", user);
        return user;
    }

    @Override
    public SysUser login(String account, String password) {
        log.info("login: {}", account);
        return userMapper.login(account, password);
    }

    @Override
    public void logout(String account) {
        log.info("logout: {}", account);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        log.info("findUserByAccount: {}", account);
        return userMapper.findUserByAccount(account);
    }

}