package com.leone.boot.common.design.behavior.visitor;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class SubWest2 extends West{

    @Override
    public void goWest1(SubEast1 east) {
        System.out.println("SubWest2 + " + east.myName1());
    }
    
    @Override
    public void goWest2(SubEast2 east) {
        System.out.println("SubWest2 + " + east.myName2());
    }

}