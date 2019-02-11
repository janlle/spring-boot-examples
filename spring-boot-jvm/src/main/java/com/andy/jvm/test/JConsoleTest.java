package com.andy.jvm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class JConsoleTest {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("start...");
        fill(10000);
    }

    private static void fill(int n) throws InterruptedException {
        List<JConsoleTest> jlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread.sleep(100);
            System.out.println("new instance...");
            jlist.add(new JConsoleTest());
        }
    }


}
