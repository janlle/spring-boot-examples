package com.andy.spring.scan;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 11:17
 **/
public class Teacher {

    public Teacher() {
        System.out.println("constructor-Teacher");
    }

    public void init(String name) {
        System.out.println("init-Teacher");
    }

    public void destroy(String name) {
        System.out.println("destroy-Teacher");
    }

    public void hello(String name) {
        System.out.println("hello:" + name);
    }

    public String hi(String name) {
        System.out.println("hi: " + name);
        return name;
    }

}
