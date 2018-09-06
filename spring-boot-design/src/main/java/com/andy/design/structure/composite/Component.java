package com.andy.design.structure.composite;

/**
 * @author Leone
 * @since 2018-08-01
 **/
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public Component() {
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display(int depth);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}