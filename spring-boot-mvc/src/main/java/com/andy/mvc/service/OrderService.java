package com.andy.mvc.service;

import org.springframework.stereotype.Service;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 22:37
 **/
@Service
public class OrderService {


    public void init() {
        System.out.println("@Bean-init-method-在构造函数执行完之后执行");
    }

    public OrderService() {
        System.out.println("初始化构造函数-OrderService");
    }

    public void destroy() {
        System.out.println("@Bean-destroy-method-在bean销毁之前执行");
    }


}
