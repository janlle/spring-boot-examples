package com.andy.common.service;

import com.andy.common.entity.User;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-26
 **/
public interface UserService {

    Integer delete(Long userId);

    User update(User user);

    User save(User user);

    User findOne(Long userId);

    List<User> list();

}
