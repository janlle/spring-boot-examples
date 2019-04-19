package com.leone.jvm.test;

/**
 * <p> 测试占内存溢出
 *
 * @author leone
 * @since 2019-02-03
 **/
public class StackDeepTest {

    private static int count = 0;

    public static void recursion() {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            // deep of calling =24840
            // deep of calling =22781
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling =" + count);
            e.printStackTrace();
        }
    }
}