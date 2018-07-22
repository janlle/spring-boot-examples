package com.andy.shiro.service;

import com.andy.shiro.entity.rbac.User;

public interface UserService {

    User getByAccount(String username);

}