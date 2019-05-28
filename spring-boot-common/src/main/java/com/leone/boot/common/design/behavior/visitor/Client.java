package com.leone.boot.common.design.behavior.visitor;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Client {

    public static void main(String[] args) {

        // 组合1
        East east = new SubEast1();
        West west = new SubWest1();
        east.goEast(west);

        // 组合2
        east = new SubEast1();
        west = new SubWest2();
        east.goEast(west);

    }

}