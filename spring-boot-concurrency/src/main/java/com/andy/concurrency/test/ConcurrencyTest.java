package com.andy.concurrency.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-22 16:28
 **/
@Slf4j
public class ConcurrencyTest {

    public static int clientTotal = 10000;
    public static int threadTotal = 50;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
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

    private static void add() {

        count ++;
    }


}
