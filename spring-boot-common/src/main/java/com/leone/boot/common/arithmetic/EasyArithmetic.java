package com.leone.boot.common.arithmetic;

import java.util.Stack;

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
        System.out.println(isValid("}{"));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * for (int i = 0; i < s.length()-1; i++) {
     * char c = s.charAt(i);
     * char c1= s.charAt(i+1);
     * if (c == '(' || c == '[' || c == '{') {
     * stack1.push(c);
     * if (c == '(' && ( stack.peek() == ')' || c1==')' ) ){
     * stack1.pop();
     * }
     * if (c == '[' && (stack.peek() == ']' || c1==']' )){
     * stack1.pop();
     * }
     * if (c == '{' && (stack.peek() == '}'  || c1=='}' )){
     * stack1.pop();
     * }
     * }
     * }
     *
     * @param s
     * @return
     */
    private static boolean isValid(String s) {
        Stack<Character> a = new Stack<>();
        Stack<Character> b = new Stack<>();
        if ("".equals(s)) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            a.push(s.charAt(i));
        }

        for (int i = 0; i < s.length() - 1; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);
            if (c1 == '(' || c1 == '[' || c1 == '{') {
                b.push(c1);
                if (c1 == '(' && (a.peek() == ')' || c2 == ')')) {
                    b.pop();
                }
                if (c1 == '[' && (a.peek() == ']' || c2 == ']')) {
                    b.pop();
                }
                if (c1 == '{' && (a.peek() == '}' || c2 == '}')) {
                    b.pop();
                }
            }
        }
        return b.isEmpty();
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * @param strs
     * @return
     */
    private static String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length < 1) {
            return result;
        }
        int minLength = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            if (minLength > strs[i].length()) {
                minLength = strs[i].length();
            }
        }

        for (int i = 0; i < minLength; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (ch != strs[j].charAt(i)) {
                    return result;
                }
            }
            result += ch;
        }
        return result;
    }


    /**
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * I             1      *
     * V             5
     * X             10     *
     * L             50
     * C             100    *
     * D             500
     * M             1000
     *
     * @param str
     * @return
     */
    private static int romanToInt(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            switch (s) {
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    break;

            }
            if (i != 0) {
                if ((s == 'V' || s == 'X') && str.charAt(i - 1) == 'I') {
                    result -= 1 * 2;
                }
                if ((s == 'L' || s == 'C') && str.charAt(i - 1) == 'X') {
                    result -= 10 * 2;
                }
                if ((s == 'D' || s == 'M') && str.charAt(i - 1) == 'C') {
                    result -= 100 * 2;
                }
            }

        }
        return result;
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
