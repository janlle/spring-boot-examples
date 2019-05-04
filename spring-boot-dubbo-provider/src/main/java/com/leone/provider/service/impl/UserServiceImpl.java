package com.leone.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leone.common.entity.User;
import com.leone.common.utils.EntityFactory;
import com.leone.dubbo.api.UserService;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产者
 *
 * @author leone
 * @since 2018-06-03
 **/
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
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
        List<User> collect = EntityFactory.userList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(collect)) {
            return null;
        } else {
            return collect.get(0);
        }
    }

    @Override
    public List<User> list() {
        return EntityFactory.userList;
    }

}
