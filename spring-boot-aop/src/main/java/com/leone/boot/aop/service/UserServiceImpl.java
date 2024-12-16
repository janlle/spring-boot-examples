package com.leone.boot.aop.service;

import com.leone.boot.aop.anno.LogAop;
import com.leone.boot.aop.interf.UserService;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author leone
 * @since 2018-06-21
 **/
@Service
public class UserServiceImpl implements UserService {

    @LogAop(description = "findOne", value = "value", name = "test")
    public User findOne(Long userId) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("UserService exception");
        }
        return EntityFactory.getUser(2L);
    }

    public Integer delete(Long userId) {
        return null;
    }

    public User update(User user) {
        return null;
    }

    public User save(User user) {
        return null;
    }

    public List<User> list() {
        return null;
    }
}
