package com.leone.aop.interf;

import com.leone.common.entity.Order;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-26
 **/
public interface OrderService {

    Order save();

    Order findOne(Long orderId);

    List<Order> list(Long userId);

}
