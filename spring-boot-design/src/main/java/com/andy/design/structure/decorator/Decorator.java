package com.andy.design.structure.decorator;

/**
 * @author Leone
 * @since: 2018-08-01
 **/
public abstract class Decorator extends Drinks {

    protected Drinks drinks;

    public Decorator(Drinks drinks) {
        this.drinks = drinks;
    }
}
