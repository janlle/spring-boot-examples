package com.leone.boot.aop.service;

import com.leone.boot.aop.anno.SystemLogAnno;
import com.leone.boot.aop.interf.UserService;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author leone
 * @since 2018-06-21
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    @SystemLogAnno(description = "获取用户", value = "value", name = "基于自定义注解的aop")
    public User findOne(Long userId) {
        log.info("findOne(Long userId)");
        if (new Random().nextBoolean()) {
            throw new RuntimeException("UserService发生异常");
        }
        return EntityFactory.getUser(2L);
    }


    @Override
    public Integer delete(Long userId) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> list() {
        return null;
    }
}
