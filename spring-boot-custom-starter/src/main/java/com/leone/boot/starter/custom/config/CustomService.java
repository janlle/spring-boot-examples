package com.leone.boot.starter.custom.config;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-01
 **/
public class CustomService {

    private String name;

    private Integer id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String info() {
        return "name: " + name + " id: " + id;
    }

    public String say() {
        return name + " " + id;
    }

}