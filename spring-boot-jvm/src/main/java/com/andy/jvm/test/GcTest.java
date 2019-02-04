package com.andy.jvm.test;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-04
 **/
public class GcTest {

    private static final int m = 1024 * 1024;

    public static void main(String[] args) {
        byte[] o1 = new byte[4 * m];
        byte[] o2 = new byte[4 * m];
        byte[] o3 = new byte[4 * m];
        byte[] o4 = new byte[4 * m];
    }

}
