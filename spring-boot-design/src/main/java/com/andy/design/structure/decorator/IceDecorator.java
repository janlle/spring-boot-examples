package com.andy.design.structure.decorator;

/**
 * @author Leone
 * @since 2018-08-01
 **/
public class IceDecorator extends Decorator {

    public IceDecorator(Drinks drinks) {
        super(drinks);
    }

    public void addIce() {
        System.out.println("加冰");
    }

    @Override
    public int price() {
        return 5 + drinks.price();
    }

    @Override
    public String getName() {
        return "加冰的" + drinks.getName();
    }


}
