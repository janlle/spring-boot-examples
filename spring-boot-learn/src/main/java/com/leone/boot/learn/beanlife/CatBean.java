package com.leone.boot.learn.beanlife;

import jakarta.annotation.Resource;

/**
 * @author leone
 * @since 2018-07-01
 **/
public class CatBean {

    private String name;

    @Resource
    private DogBean dogBean;

    public CatBean() {
        this.name = "Tom";
        System.out.println("构造方法-CatBean");
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
