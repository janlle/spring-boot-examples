package com.leone.boot.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-09
 **/
public class JdkProxy {

    interface IUserService {
        Integer delete(Integer userId);
    }

    static class UserServiceImpl implements IUserService {
        @Override
        public Integer delete(Integer userId) {
            // 业务
            System.out.println("delete user");
            return userId;
        }
    }

    // 自定义InvocationHandler
    static class UserServiceProxy implements InvocationHandler {
        // 目标对象
        private Object target;

        public UserServiceProxy(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("------方法调用前---------");
            //执行相应的目标方法
            Object result = method.invoke(target, args);
            System.out.println("------方法调用后---------");
            return result;
        }
    }

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        // 创建调用处理类
        UserServiceProxy handler = new UserServiceProxy(userService);
        // 得到代理类实例
        IUserService proxy = (IUserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), new Class[]{IUserService.class}, handler);
        // 调用代理类的方法
        Integer userId = proxy.delete(3);
        System.out.println(userId);
    }

}
