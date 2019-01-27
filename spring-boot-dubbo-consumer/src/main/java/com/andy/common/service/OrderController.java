package com.andy.common.service;

import com.andy.common.beans.order.OrderVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Leone
 * @since 2018-06-03
 **/
@RestController
@RequestMapping("/api")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/{orderId}")
    public OrderVO findOne(@PathVariable("orderId") Long orderId) {
        return orderService.findOne(orderId);
    }
}
