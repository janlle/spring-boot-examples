package com.leone.design.structure.decorator;

/**
 * @author leone
 * @since 2018-08-01
 **/
public class Coke extends Drinks {

    public Coke() {
        name = "可乐";
    }

    @Override
    public int price() {
        return 30;
    }

}
