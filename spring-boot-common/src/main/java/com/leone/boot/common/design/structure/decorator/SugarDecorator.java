package com.leone.boot.common.design.structure.decorator;

/**
 * @author leone
 * @since 2018-08-01
 **/
public class SugarDecorator extends Decorator {

    public SugarDecorator(Drinks drinks) {
        super(drinks);
    }

    public void addSugar() {
        System.out.println("加糖");
    }

    @Override
    public int price() {
        return 10 + drinks.price();
    }

    @Override
    public String getName() {
        return "加糖的" + drinks.getName();
    }
}
