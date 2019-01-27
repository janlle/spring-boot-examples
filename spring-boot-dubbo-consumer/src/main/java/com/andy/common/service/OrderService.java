package com.andy.common.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.andy.common.beans.order.OrderVO;
import com.andy.common.entity.Order;
import com.andy.common.entity.User;
import com.andy.common.utils.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    @Reference(version = "1.0.0", check = false)
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
        return vo;
    }

}
