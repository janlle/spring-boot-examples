package com.leone.boot.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
 *
 * @author leone
 * @since 2018-05-08
 **/
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.schedule(() -> {
                log.info("thread: {}", Thread.currentThread().getName());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 3, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

}
