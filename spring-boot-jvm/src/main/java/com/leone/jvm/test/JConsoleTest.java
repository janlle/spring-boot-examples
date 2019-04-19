package com.leone.jvm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class JConsoleTest {

    private Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
        System.out.println("start...");

        JConsoleTest obj = new JConsoleTest();
        obj.threadTest();
        obj.thread();
//        fill(10000);
    }

    private static void fill(int n) throws InterruptedException {
        List<JConsoleTest> jlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread.sleep(100);
            System.out.println("new instance...");
            jlist.add(new JConsoleTest());
        }
    }


    private void threadTest() {
        Scanner scanner = new Scanner(System.in);

        scanner.next();

        new Thread(() -> {
            while (true) {
            }
        }, "thread-a").start();


    }

    private void thread() {
        new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    object.notify();
                }
            }
        }, "thread-b").start();
    }


}
