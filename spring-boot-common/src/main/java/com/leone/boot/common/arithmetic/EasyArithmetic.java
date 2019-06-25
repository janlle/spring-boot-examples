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
        System.out.println(strStr("bbaa", "aab"));
    }


    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 1: 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * <p>
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * <p>
     * 注意这五个元素可为任意顺序,你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeElement(int[] nums, int val) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[a++] = nums[i];
            }
        }
        return a;
    }

    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {

        return null;
    }


    /**
     * 给定数组 nums = [1,1,2],
     * <p>
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[number]) {
                nums[++number] = nums[i];
            }
        }
        return ++number;
    }


    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * @param s
     * @return
     */
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if ("".equals(s)) {
            return true;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
            } else if ((chars[i] == ')' && stack.peek() == '(') || (chars[i] == ']' && stack.peek() == '[') || (chars[i] == '}' && stack.peek() == '{')) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
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
