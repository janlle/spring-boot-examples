package com.leone.shiro.service;

import com.leone.shiro.entity.rbac.User;

public interface UserService {

    User getByAccount(String username);

}