package com.andy.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: Mr.ruoLin
 * @since: 2018-05-06 18:36
 **/
@Slf4j
public class SemaphoreExample2 {

    private static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    //尝试获取许可
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        semaphore.release();//释放许可
                    }

                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executorService.shutdown();
        log.info("finish");

    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }

}
