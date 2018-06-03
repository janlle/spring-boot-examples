package com.andy.dubbo.common;

import com.andy.dubbo.consumer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-03 11:17
 **/
@RestController
public class UserController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/user")
    public User user(String username) {
        return orderService.createOrder(username);
    }
}
