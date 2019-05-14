package com.leone.boot.jvm.test;

/**
 * <p> -XX:+PrintGCDetails -verbose:gc -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m XX:SurvivorRatio=8
 *
 * @author leone
 * @since 2019-02-04
 **/
public class GcTest {

    private static final int m = 1024 * 1024;

    public static void main(String[] args) {
//        byte[] o1 = new byte[4 * m];
//        byte[] o2 = new byte[4 * m];
//        byte[] o3 = new byte[4 * m];
//        byte[] o4 = new byte[4 * m];

        for (int i = 0; i < 10000; i++) {
            System.out.println(System.nanoTime() + "--" + System.currentTimeMillis());
        }

    }

}
