package com.andy.design.structure.adapter;

public class Monkey extends Adapter {

    @Override
    public void eat() {
        System.out.println("monkey eat");
    }

    @Override
    public void sleep() {
        System.out.println("monkey sleep");
    }
}