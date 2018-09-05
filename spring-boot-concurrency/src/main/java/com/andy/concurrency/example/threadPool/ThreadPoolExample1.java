package com.andy.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Leone
 * @since: 2018-05-08 21:13
 **/
@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("thread:{}", a);
                }
            });
        }
        executorService.shutdown();
    }

}
