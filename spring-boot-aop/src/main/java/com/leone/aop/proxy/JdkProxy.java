package com.leone.aop.proxy;

import com.leone.aop.interf.UserService;
import com.leone.common.entity.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-09
 **/
public class JdkProxy {

    // 自定义InvocationHandler
    static class UserServiceProxy implements InvocationHandler {

        // 目标对象
        private Object target;

        public UserServiceProxy(Object target) {
            this.target = target;
        }

        public UserServiceProxy() {
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
        UserService userService = new UserServiceImpl();

        // 创建调用处理类
        UserServiceProxy handler = new UserServiceProxy(userService);
        // 得到代理类实例
        UserService proxy = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), new Class[]{UserService.class}, handler);
        // 调用代理类的方法
        User user = proxy.save(new User());
    }


}
