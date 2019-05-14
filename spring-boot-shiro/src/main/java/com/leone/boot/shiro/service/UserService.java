package com.leone.boot.shiro.service;

import com.leone.boot.shiro.entity.rbac.User;

public interface UserService {

    User getByAccount(String username);

}