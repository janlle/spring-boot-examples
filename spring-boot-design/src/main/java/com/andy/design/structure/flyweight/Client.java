package com.andy.design.structure.flyweight;

/**
 * @author: Leone
 * @since: 2018-07-31
 **/
public class Client {

    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));

        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }

}