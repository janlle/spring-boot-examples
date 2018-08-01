package com.andy.design.structure.decorator;

/**
 * @author: lyon
 * @since: 2018-08-01
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
