package com.leone.boot.spring.life;

/**
 * @author leone
 * @since 2018-07-01
 **/
public class StudentBean {

    //@Resource(name = "person")
    private PersonBean person;

    public StudentBean() {
        System.out.println("constructor-StudentBean");
    }

    public void init(String name) {
        System.out.println("init-StudentBean");
    }

    public void destroy(String name) {
        System.out.println("destroy-StudentBean");
    }

    public void hello(String name) {
        System.out.println("hello:" + name);
    }

    public String hi(String name) {
        System.out.println("hi: " + name);
        return name;
    }

}
