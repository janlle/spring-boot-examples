package com.leone.boot.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-09
 **/
public class CglibProxy {

    static class UserService implements MethodInterceptor {

        private Object target;

        /**
         * 业务方法
         *
         * @param userId
         * @return
         */
        public Integer delete(Integer userId) {
            System.out.println("delete user");
            return userId;
        }

        /**
         * 利用Enhancer类生成代理类
         *
         * @param target
         * @return
         */
        public Object getInstance(Object target) {
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
            System.out.println("------方法调用前---------");
            Object object = methodProxy.invokeSuper(o, objects);
            System.out.println("------方法调用后---------");
            return object;
        }

    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        UserService proxy = (UserService) userService.getInstance(userService);
        Integer userId = proxy.delete(2);
        System.out.println(userId);
    }

}
