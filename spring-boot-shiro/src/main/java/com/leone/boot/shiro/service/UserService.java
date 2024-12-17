package com.leone.boot.shiro.service;


import com.leone.boot.shiro.entity.SysUser;

public interface UserService {

    SysUser findAllPermissionByAccount(String account);

    SysUser login(String account, String password);

    void logout(String account);

    SysUser findUserByAccount(String account);

}