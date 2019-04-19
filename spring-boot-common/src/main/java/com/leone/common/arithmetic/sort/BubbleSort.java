package com.leone.common.arithmetic.sort;

import java.util.Arrays;

/**
 * <p> 冒泡排序
 *
 * @author leone
 * @since 2019-03-14
 **/
public class BubbleSort {

    private static int[] arr = {5, 9, 8, 1, 3, 12, 5, 30};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
