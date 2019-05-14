package com.leone.boot.jvm.test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-03
 **/
public class RefTest {

    private Object ins;

    public static void main(String[] args) {

        RefTest re1 = new RefTest();

        RefTest re2 = new RefTest();

        re1.ins = re2;

        re2.ins = re1;

        re1 = null;

        re2 = null;

        System.gc();

    }

}
