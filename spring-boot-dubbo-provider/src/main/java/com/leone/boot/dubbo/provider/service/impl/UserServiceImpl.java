package com.leone.boot.dubbo.provider.service.impl;

import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import com.leone.boot.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产者
 *
 * @author leone
 * @since 2018-06-03
 **/
@Service(version = "1.0.0")
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
