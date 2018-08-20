package com.andy.concurrency.example.commonUnsafe;

import com.andy.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: Leone
 * @since: 2018-05-06 15:37
 **/
@Slf4j
@NotThreadSafe
public class StringExample2 {

    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size={}", stringBuffer.length());
    }

    private static void update() {
        stringBuffer.append("1");
    }


}
