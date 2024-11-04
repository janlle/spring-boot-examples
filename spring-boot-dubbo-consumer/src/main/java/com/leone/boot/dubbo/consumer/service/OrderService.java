package com.leone.boot.dubbo.consumer.service;

import com.leone.boot.dubbo.api.UserService;
import com.leone.boot.dubbo.api.bean.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 消费者
 *
 * @author leone
 * @since 2018-06-03
 **/
@Component
public class OrderService {

    @DubboReference(url = "dubbo://127.0.0.1:12345")
    private UserService userService;

    /**
     * @param orderId orderId
     * @return order
     */
    public Map<String, String> findOne(Long orderId) {
        User user = userService.findOne(1L);
        Map<String, String> map = new HashMap<>();
        map.put("order_id", orderId.toString());
        map.put("account", user.getAccount());
        map.put("date", user.getCreateTime().toString());
        return map;
    }


}
