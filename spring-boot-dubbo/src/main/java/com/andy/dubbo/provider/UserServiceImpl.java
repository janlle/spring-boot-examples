package com.andy.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.andy.dubbo.common.User;
import com.andy.dubbo.common.UserService;
import com.andy.dubbo.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Leone
 * @since 2018-06-03 10:44
 **/
@Slf4j
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    List<User> userList = new LinkedList<>();

    {
        for (long i = 0; i < 10; i++) {
            User user = EntityFactory.getUser();
            userList.add(user);
        }
    }

    @Override
    public User getUser(String username) {
        if (username == null) {
            return null;
        }
        User user = null;
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getAccount())) {
                user = userList.get(i);
            }
        }
        return user;
    }
}
