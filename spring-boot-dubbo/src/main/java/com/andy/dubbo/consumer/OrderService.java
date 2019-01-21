package com.andy.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.andy.dubbo.common.UserService;
import com.andy.dubbo.entity.Order;
import com.andy.dubbo.entity.OrderVO;
import com.andy.dubbo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消费者
 *
 * @author Leone
 * @since 2018-06-03
 **/
@Slf4j
@Component
public class OrderService {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:20880")
    private UserService userService;

    private static List<Order> orderList = new ArrayList<>();

    static {
        orderList.add(new Order(1, "10923942233", 101, 120, new Date()));
        orderList.add(new Order(1, "10923942234", 102, 157, new Date()));
        orderList.add(new Order(1, "10923942235", 103, 182, new Date()));
        orderList.add(new Order(1, "10923942236", 104, 185, new Date()));
        orderList.add(new Order(1, "10923942237", 105, 167, new Date()));
    }

    /**
     * @param orderId
     * @return
     */
    public OrderVO findOne(Integer orderId) {
        List<Order> collect = orderList.stream().filter(e -> e.getOrderId().equals(orderId)).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(collect)) {
            return null;
        }
        Order order = collect.get(0);
        User user = userService.findOne(order.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        log.info("user:{}", user);
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(order, vo);
        vo.setUserAccount(user.getAccount());
        vo.setUserAge(user.getAge());
        return vo;
    }

}
