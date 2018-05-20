package com.andy.pay.test;

import com.andy.pay.PayApplication;
import com.andy.pay.common.model.Order;
import com.andy.pay.common.model.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 20:04
 **/
@Slf4j
@SpringBootTest(classes = PayApplication.class)
@RunWith(SpringRunner.class)
public class OrderMapperTest {

    String orderId = "1001";

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test() {
        Order order = orderMapper.selectByOrderId(orderId);
        log.info("order:{}", order);
    }




}
