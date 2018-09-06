package com.andy.spring.beans;

/**
 * @author Leone
 * @since 2018-06-29 23:21
 **/
public class Person {

    public Person() {
        System.out.println("constructor-person");
    }

    public void init(String name) {
        System.out.println("init-person");
    }

    public void destroy(String name) {
        System.out.println("destroy-person");
    }

    public void hello(String name) {
        System.out.println("hello:" + name);
    }

    public String hi(String name) {
        System.out.println("hi: " + name);
        return name;
    }


}
