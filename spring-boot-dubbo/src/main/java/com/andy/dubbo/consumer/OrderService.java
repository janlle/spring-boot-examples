package com.andy.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.andy.dubbo.common.User;
import com.andy.dubbo.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.lyon
 * @createBy: 2018-06-03 10:45
 **/
@Slf4j
@Service
public class OrderService {

    @Reference(version = "1.0.0")
    private UserService userService;

    public User createOrder(String username) {
        User user = userService.getUser(username);
        log.info("user:{}", user);
        return user;
    }

}
