package com.andy.starter.config;

import org.springframework.stereotype.Component;

/**
 * @author: lyon
 * @since: 2018-05-12
 **/
@Component
public class HelloService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String sayHello() {
        return "customer-property " + msg;
    }

}