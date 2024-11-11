package com.leone.boot.dubbo.consumer.controller;

import com.leone.boot.dubbo.consumer.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.Map;

/**
 * @author leone
 * @since 2018-06-03
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public Map<String,String> findOne(@PathVariable("orderId") Long orderId) {
        return orderService.findOne(orderId);
    }
}
