package com.leone.boot.limited.arithmetic.counter;

/**
 * <p> 限流算法之计数器算法
 * <p>
 * 一定时间内只允许一定数量的请求访问
 * 但是有一个十分致命的问题，那就是临界问题
 *
 * @author leone
 * @since 2019-04-19
 **/
public class CounterTest {

    private static long timeStamp = nowTime();

    // 限制的请求数量
    private static long limitCount = 100;

    // 限制的时间间隔单位 millisecond
    private static long interval = 1000;

    // 当前的请求数量
    private static long reqCount = 0;


    public static boolean grant() {
        long now = nowTime();
        if (now < timeStamp + interval) {
            if (reqCount < limitCount) {
                reqCount++;
                return true;
            } else {
                return false;
            }
        } else {
            timeStamp = nowTime();
            reqCount = 0;
            return false;
        }
    }

    private static long nowTime() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                if (grant()) {
                    System.out.println("exec success");
                } else {
                    System.out.println("limited");
                }
            }).start();
            Thread.sleep(50);
        }
    }


}
