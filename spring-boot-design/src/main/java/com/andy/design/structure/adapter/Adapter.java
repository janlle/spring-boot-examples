package com.andy.design.structure.adapter;

public class Adapter implements Animal {

    @Override
    public void eat() {
        System.out.println("eat");
    }

    @Override
    public void sleep() {
        System.out.println("sleep");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}