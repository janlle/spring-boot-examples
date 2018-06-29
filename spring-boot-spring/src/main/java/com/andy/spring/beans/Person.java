package com.andy.spring.beans;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 23:21
 **/
public class Person {

    public Person() {
        System.out.println("constructor-person");
    }


    public void hello(String name) {
        System.out.println("hello:" + name);
    }

}
