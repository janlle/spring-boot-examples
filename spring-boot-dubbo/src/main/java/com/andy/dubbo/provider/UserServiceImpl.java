package com.andy.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.andy.dubbo.common.UserService;
import com.andy.dubbo.entity.User;
import com.andy.dubbo.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产者
 *
 * @author Leone
 * @since 2018-06-03
 **/
@Slf4j
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    private List<User> userList = EntityFactory.getUsers(100);

    @Override
    public User getUser(String username) {
        if (username == null) {
            return null;
        }
        List<User> collect = userList.stream().filter(e -> e.getAccount().equals(username)).collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0);
        }
        return null;
    }


}
