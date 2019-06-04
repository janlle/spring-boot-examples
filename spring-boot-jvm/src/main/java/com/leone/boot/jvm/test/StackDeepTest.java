package com.leone.boot.jvm.test;

/**
 * <p> 测试占内存溢出
 *
 * @author leone
 * @since 2019-02-03
 **/
public class StackDeepTest {

    private static int count = 0;

    public static void recursion() {
        byte[][] bytes = new byte[1024][1024];
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            // deep of calling =24840
            // deep of calling =22781
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}