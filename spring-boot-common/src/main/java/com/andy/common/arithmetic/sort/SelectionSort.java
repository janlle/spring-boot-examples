package com.andy.common.arithmetic.sort;

import java.util.Arrays;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-14
 **/
public class SelectionSort {

    private static int[] arr = {5, 9, 8, 1, 3, 12, 5, 30};

    public static void main(String[] args) {
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

}
