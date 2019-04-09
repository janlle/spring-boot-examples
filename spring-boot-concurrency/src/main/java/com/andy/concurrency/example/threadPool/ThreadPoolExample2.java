package com.andy.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中
 * FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
 *
 * @author Leone
 * @since 2018-05-08
 **/
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        // 指定线程池的大小为20
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 50; i++) {
            threadPool.execute(() -> {
                        log.info("thread: {}", Thread.currentThread().getName());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        threadPool.shutdown();
    }

}
