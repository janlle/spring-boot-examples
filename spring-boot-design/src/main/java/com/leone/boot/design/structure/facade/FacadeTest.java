package com.leone.boot.design.structure.facade;

/**
 * @author leone
 * @cerateBy: 2018-07-29
 **/
public class FacadeTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
