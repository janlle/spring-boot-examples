package com.leone.boot.dubbo.consumer.service;

import com.leone.boot.common.beans.order.OrderVO;
import com.leone.boot.common.entity.Order;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import com.leone.boot.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消费者
 *
 * @author leone
 * @since 2018-06-03
 **/
@Component
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private UserService userService;

    /**
     * @param orderId
     * @return
     */
    public OrderVO findOne(Long orderId) {
        List<Order> collect = EntityFactory.orderList.stream().filter(e -> e.getOrderId().equals(orderId)).collect(Collectors.toList());
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
        vo.setUserDescription(user.getDescription());
        return vo;
    }


}
