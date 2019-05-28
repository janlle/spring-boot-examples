package com.leone.boot.common.design.behavior.visitor;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class SubEast1 extends East{

    @Override
    public void goEast(West west) {
        west.goWest1(this);
    }
    
    public String myName1(){
        return "SubEast1";
    }
}