package com.andy.design.structure.facade;

/**
 * @author: lyon
 * @cerateBy: 2018-07-29
 **/
public class FacadeTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
