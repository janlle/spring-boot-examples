package com.andy.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.andy.dubbo.common.UserService;
import com.andy.dubbo.entity.Order;
import com.andy.dubbo.entity.User;
import com.andy.dubbo.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 消费者
 *
 * @author Leone
 * @since 2018-06-03
 **/
@Slf4j
@Component
public class OrderService {

    @Reference(version = "1.0.0")
    private UserService userService;

    public Order createOrder(String username) {
        User user = userService.getUser(username);
        log.info("user:{}", user);
        Order order = new Order();
        order.setOrderId(200);
        order.setOrderNo(1 + RandomUtil.getNum(11));
        order.setCreateTime(new Date());
        order.setUser(user);
        return order;
    }

}
