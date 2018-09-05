package com.andy.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Leone
 * @since: 2018-05-08 20:30
 **/
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do some thing callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        log.info("do some thing main");
        Thread.sleep(1000);

        String result = future.get();
        log.info("result:{}", result);
    }
}
