package com.leone.jvm.test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class JpsTest {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        for (int i = 0; i < 1000; i++) {
            System.out.println("input: " + next);
            byte[] b = new byte[1024 * 1024];
            TimeUnit.SECONDS.sleep(1);
        }
        scanner.next();
    }

}
