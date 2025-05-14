package com.leone.boot.learn.beanlife;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-27
 **/
public class DogBean {

    @Resource
    private CatBean catBean;

    private String name;

    public DogBean() {
        this.name = "Spike";
        System.out.println("构造方法-DogBean");
    }

    public void init() {
        System.out.println("init-" + name);
    }

    public void destroy() {
        System.out.println("destroy-" + name);
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
