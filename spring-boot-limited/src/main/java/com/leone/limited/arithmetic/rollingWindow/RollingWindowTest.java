package com.leone.limited.arithmetic.rollingWindow;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
public class RollingWindowTest {

    /**
     * 计算器
     */
    private AtomicLong counter = new AtomicLong(0);
    /**
     * 初始时间
     */
    private static long timestamp = System.currentTimeMillis();
    /**
     * 时间窗口内最大请求个数
     */
    private long limit;

    public RollingWindowTest(long limit) {
        this.limit = limit;
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        //1s之内的请求
        if (now - timestamp < 1000) {
            if (counter.get() < limit) {
                counter.incrementAndGet();
                System.out.println("pass_request");
                return true;
            } else {
                System.out.println("refuse_request");
                return false;
            }
        } else {
            counter = new AtomicLong(0);
            timestamp = now;
            System.out.println("time_end,refuse_request");
            return false;
        }
    }

    public static void main(String[] args) {
        RollingWindowTest limit = new RollingWindowTest(10);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                double random = (new Random()).nextDouble();
                long a = (long) (random * 1000);
                try {
                    //睡眠一下
                    Thread.sleep(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                limit.tryAcquire();
            });
        }
        executor.shutdown();
    }


}
