package com.andy.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Leone
 * @since 2018-05-08 21:13
 **/
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int a = i;
            executorService.schedule(() -> {
                    log.info("thread:{}", a);
                }, 3, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

}
