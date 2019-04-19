package com.leone.aop.proxy;

import com.leone.aop.interf.OrderService;
import com.leone.common.entity.Order;
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
        System.out.println("findOne");
        return null;
    }

    @Override
    public List<Order> list(Long userId) {
        System.out.println("list");
        return null;
    }

    /**
     * 利用Enhancer类生成代理类
     *
     * @return
     */
    public Object getInstance(Object target) {
        // 给业务对象赋值
        this.target = target;

        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(target.getClass());
        // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();

    }


    /**
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before:" + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("After:" + method.getName());
        return object;
    }


}
