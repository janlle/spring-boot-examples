package com.leone.design.structure.decorator;

/**
 * @author leone
 * @since 2018-08-01
 **/
public class Juice extends Drinks {

    public Juice() {
        name = "果汁";
    }

    @Override
    public int price() {
        return 0;
    }

}
