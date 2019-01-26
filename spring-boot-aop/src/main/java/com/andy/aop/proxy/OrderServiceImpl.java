package com.andy.aop.proxy;

import com.andy.common.entity.Order;
import com.andy.common.service.OrderService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-09
 **/
public class OrderServiceImpl implements OrderService, MethodInterceptor {

    private Object target;

    @Override
    public Order save() {
        System.out.println("save");
        return null;
    }

    @Override
    public Order findOne(Long orderId) {
        return null;
    }

    @Override
    public List<Order> list(Long userId) {
        return null;
    }

    /**
     * 利用Enhancer类生成代理类
     *
     * @return
     */
    public static Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceImpl.class);
        enhancer.setCallback(new OrderServiceImpl());
        OrderServiceImpl userService = (OrderServiceImpl) enhancer.create();
        return enhancer.create();
    }


    /**
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before:" + method);
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("After:" + method);
        return object;
    }


}
