package com.andy.spring.beans;

import com.andy.spring.scan.Teacher;

import javax.annotation.Resource;

/**
 * @author Leone
 * @since: 2018-07-01 11:12
 **/
public class Student {

    @Resource(type = Teacher.class)
    private Teacher teacher;

    @Resource(name = "person")
    private Person person;

    public Student() {
        System.out.println("constructor-Student");
    }

    public void init(String name) {
        System.out.println("init-Student");
    }

    public void destroy(String name) {
        System.out.println("destroy-Student");
    }

    public void hello(String name) {
        System.out.println("hello:" + name);
    }

    public String hi(String name) {
        System.out.println("hi: " + name);
        return name;
    }

}
