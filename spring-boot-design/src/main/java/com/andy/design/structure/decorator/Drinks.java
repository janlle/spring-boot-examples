package com.andy.design.structure.decorator;

/**
 * @author: lyon
 * @since: 2018-08-01
 **/
public abstract class Drinks {

    String name;

    public abstract int price();

    public String getName() {
        return name;
    }
}
