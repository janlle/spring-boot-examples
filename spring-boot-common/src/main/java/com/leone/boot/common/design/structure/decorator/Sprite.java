package com.leone.boot.common.design.structure.decorator;

/**
 * @author leone
 * @since 2018-08-01
 **/
public class Sprite extends Drinks {

    public Sprite() {
        name = "雪碧";
    }

    @Override
    public int price() {
        return 10;
    }

}
