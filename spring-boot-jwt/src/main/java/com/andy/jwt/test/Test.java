package com.andy.jwt.test;

/**
 * <p>
 *
 * @author Leone
 * @since: 2018-08-27
 **/
public class Test {

    public static void main(String[] args) {
        int sum = 10;
        char[] chars = {'a', 'b', 'c'};

        String s = "hello";

        test1(sum);
        test2(chars);
        test3(s);

        System.out.println(sum);
        System.out.println(chars);
        System.out.println(s);
    }


    public static void test1(int sum) {
        sum = 20;
    }

    public static void test2(char[] chars) {
        chars[0] = 'd';
        chars = new char[]{'y', 'x'};
    }

    public static void test3(String s) {
        s = "world";
    }


}
