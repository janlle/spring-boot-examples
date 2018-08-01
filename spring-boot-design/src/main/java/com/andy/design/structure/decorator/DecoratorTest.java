package com.andy.design.structure.decorator;

/**
 * @author: lyon
 * @since: 2018-08-01
 **/
public class DecoratorTest {

    public static void main(String[] args) {

        Drinks drinks = new Coke();

        drinks = new IceDecorator(drinks);

        System.out.println(drinks.getName() + drinks.price());


    }


}
