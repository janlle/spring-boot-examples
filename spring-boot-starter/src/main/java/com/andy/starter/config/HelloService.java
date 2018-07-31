package com.andy.starter.config;

/**
 * @author: lyon
 * @since: 2018-05-12 09:29
 **/
public class HelloService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String sayHello(){
        return "customer-property " + msg;
    }

}