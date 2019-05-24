package com.leone.boot.common.arithmetic;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
public class EasyArithmetic {

    private static int template(int[] arr) {
        return 0;
    }

    public static void main(String[] args) {
        for (int i = -1000; i < 1000; i++) {
            if (isPalindrome(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * @param x
     * @return
     */
    private static boolean isPalindrome(int x) {
        int oldVal = x;
        int newVal = 0;
        while (x > 0) {
            newVal = newVal * 10 + x % 10;
            x /= 10;
        }
        return oldVal == newVal;
    }

    /**
     * 反转整数
     *
     * @param x
     * @return
     */
    private static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int a = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && a > 7)) {
                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && a < -8)) {
                return 0;
            }

            rev = rev * 10 + a;
        }
        return rev;
    }


}
