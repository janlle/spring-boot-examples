package com.leone.boot.learn.beanlife;

/**
 * @author leone
 * @since 2018-06-29
 **/
public class PersonBean {

    public PersonBean() {
        System.out.println("constructor-person");
    }

    public void init(String name) {
        System.out.println("init-person");
    }

    public void destroy(String name) {
        System.out.println("destroy-person");
    }

    public void sayHello(String message) {
        System.out.println("hello:" + message);
    }

    public int sleep(int time) {
        System.out.println("sleep: " + time);
        return time;
    }


}
