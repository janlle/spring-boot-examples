package com.andy.dubbo.common;

import com.andy.dubbo.entity.User;

/**
 * @author Leone
 * @since 2018-06-03
 **/
public interface UserService {

    User findOne(Integer userId);
}
