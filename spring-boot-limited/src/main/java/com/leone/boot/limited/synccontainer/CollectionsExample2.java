package com.leone.boot.limited.synccontainer;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author leone
 * @since 2018-05-06 16:31
 **/

public class CollectionsExample2 {

    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    private static Set<Integer> set = Collections.synchronizedSet(Sets.newHashSet());

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(set.size());
    }

    private static void update(int i) {
        set.add(i);
    }

}
