package com.leone.boot.design.structure.decorator;

/**
 * @author leone
 * @since 2018-08-01
 **/
public abstract class Drinks {

    String name;

    public abstract int price();

    public String getName() {
        return name;
    }
}
