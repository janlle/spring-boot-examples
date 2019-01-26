package com.andy.aop.proxy;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-09
 **/
public class CglibProxy {

    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceImpl proxy = (OrderServiceImpl) orderService.getInstance(orderService);
        proxy.save();
    }

}
