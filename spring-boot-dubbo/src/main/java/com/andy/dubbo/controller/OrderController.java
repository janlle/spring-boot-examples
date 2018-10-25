package com.andy.dubbo.controller;

import com.andy.dubbo.consumer.OrderService;
import com.andy.dubbo.entity.Order;
import com.andy.dubbo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leone
 * @since 2018-06-03
 **/
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public Order one(String username) {
        return orderService.createOrder(username);
    }
}
