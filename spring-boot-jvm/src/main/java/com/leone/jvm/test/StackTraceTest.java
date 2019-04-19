package com.leone.jvm.test;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class StackTraceTest {

    public static void main(String[] args) {

        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-" + Arrays.toString(map.get(entry.getKey())));
        }


    }

}
