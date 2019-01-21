package com.andy.dubbo.consumer;

import com.andy.dubbo.consumer.OrderService;
import com.andy.dubbo.entity.Order;
import com.andy.dubbo.entity.OrderVO;
import com.andy.dubbo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderVO findOne(@PathVariable("orderId") Integer orderId) {
        return orderService.findOne(orderId);
    }
}
