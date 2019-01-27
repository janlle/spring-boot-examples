package com.andy.common.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.andy.common.entity.User;
import com.andy.common.service.UserService;
import com.andy.common.utils.EntityFactory;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产者
 *
 * @author Leone
 * @since 2018-06-03
 **/
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

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
    public User findOne(Long userId) {
        List<User> collect = EntityFactory.userList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(collect)) {
            return collect.get(0);
        }
        return null;
    }

    @Override
    public List<User> list() {
        return null;
    }
}
