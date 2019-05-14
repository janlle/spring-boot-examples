package com.leone.boot.concurrency.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>模拟并发</p>
 *
 * @author leone
 * @since 2018-04-22
 **/
@Slf4j
public class ConcurrencyTest {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        // 初始化线程池
        ExecutorService executorService = Executors.newCachedThreadPool();


        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);


        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count={}", count);
    }

    /**
     * 执行原子操作
     */
    private static void add() {
        count++;
    }


}
