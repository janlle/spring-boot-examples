package com.andy.design.structure.bridge;

public class CPUDriver implements Driver {
 
    @Override
    public void connect() {
        System.out.println("connect mysql done!");
    }
}