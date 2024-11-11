package com.leone.boot.dubbo.api;

import com.leone.boot.dubbo.api.bean.User;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-26
 **/
public interface UserService {

    User update(User user);

    User save(User user);

    User findOne(Long userId);

    List<User> list();

}
