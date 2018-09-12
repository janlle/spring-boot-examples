package com.andy.dubbo.test;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-11
 **/
public class Test {
    public static void main(String[] args) {


        System.out.println(3 * 0.1 == 0.3);
        System.out.println(3 * 0.1);

        byte a = 127;
        byte b = 127;
        b = (byte)(a + b); // error : cannot convert from int to byte
        b += a; // ok

    }
}
