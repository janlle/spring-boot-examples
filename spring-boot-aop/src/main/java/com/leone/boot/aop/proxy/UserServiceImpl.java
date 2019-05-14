package com.leone.boot.aop.proxy;

import com.leone.boot.aop.interf.UserService;
import com.leone.boot.common.entity.User;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-26
 **/
public class UserServiceImpl implements UserService {

    @Override
    public Integer delete(Long userId) {
        System.out.println("delete");
        return null;
    }

    @Override
    public User update(User user) {
        System.out.println("update");
        return null;
    }

    @Override
    public User save(User user) {
        System.out.println("save");
        return null;
    }

    @Override
    public User findOne(Long userId) {
        System.out.println("delete");
        return null;
    }

    @Override
    public List<User> list() {
        System.out.println("list");
        return null;
    }
}
