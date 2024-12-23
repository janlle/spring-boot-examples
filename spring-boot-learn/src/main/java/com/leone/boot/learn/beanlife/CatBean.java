package com.leone.boot.learn.beanlife;

/**
 * @author leone
 * @since 2018-07-01
 **/
public class CatBean {

    private String name;

    public CatBean() {
        this.name = "tom";
        System.out.println("构造方法-CatBean");
    }

    public void init(String name) {
        System.out.println("init-CatBean");
    }

    public void destroy(String name) {
        System.out.println("destroy-CatBean");
    }

    public void say(String name) {
        System.out.println("mi: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
