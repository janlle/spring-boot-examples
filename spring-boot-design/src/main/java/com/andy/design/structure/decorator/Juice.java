package com.andy.design.structure.decorator;

/**
 * @author: lyon
 * @since: 2018-08-01
 **/
public class Juice extends Drinks {

    @Override
    public int price() {
        return 0;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
