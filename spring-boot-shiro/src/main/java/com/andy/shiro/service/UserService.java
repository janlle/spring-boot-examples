package com.andy.shiro.service;

import com.andy.shiro.entity.rbac.User;

public interface UserService {
    User findByUsername(String username);
}