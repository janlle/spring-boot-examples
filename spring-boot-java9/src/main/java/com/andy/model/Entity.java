package com.andy.model;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-07 17:47
 **/
public abstract class Entity {

    protected String id;

    public Entity() {
    }

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
