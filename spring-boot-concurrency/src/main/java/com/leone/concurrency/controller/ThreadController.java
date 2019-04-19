package com.leone.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-24
 **/
@Slf4j
@RestController
@RequestMapping("/thread")
public class ThreadController {

    /**
     * 1.newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

    /**
     * 2.newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    /**
     * 3.newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 4.newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     */
    static ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

    /**
     * 5.ThreadPoolExecutor这种方式创建线程池，参数很多，由于可以显示指定队列的大小，所以可以合理避免OOM
     */
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            4,      //corePoolSize:线程核心数量，及时处于idle状态，也不会回收
            10,     //maximumPoolSize:线程数的上限
            60,     //keepAliveTime:超过这个时间，超过corePoolSize的线程，多余的线程将会被回收
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(300),         //任务的排队队列  不显示指定大小，会默认Integer.MAX_VALUE，设置不当容易OOM
            new ThreadPoolExecutor.AbortPolicy()    //拒绝策略
    );


    @GetMapping("/fixed")
    public String parse() {
        Future<?> submit = fixedThreadPool.submit(() -> System.out.println("线程名称：" + Thread.currentThread().getName()));
        return Thread.currentThread().getName();
    }

    @GetMapping("/cache")
    public String cacheThreadPool() {
        Future<?> submit = cachedThreadPool.submit(() -> System.out.println("线程名称：" + Thread.currentThread().getName()));
        return Thread.currentThread().getName();
    }

    @GetMapping("/scheduled")
    public String scheduledThreadPool() {
        Future<?> submit = scheduledThreadPool.submit(() -> System.out.println("线程名称：" + Thread.currentThread().getName()));
        return Thread.currentThread().getName();
    }

    @GetMapping("/single")
    public String singleThreadPool() {
        Future<?> submit = singleThreadPool.submit(() -> System.out.println("线程名称：" + Thread.currentThread().getName()));
        return Thread.currentThread().getName();
    }

    @GetMapping("thread-port")
    public String threadPort() {
        Future<?> submit = threadPoolExecutor.submit(() -> System.out.println("线程名称：" + Thread.currentThread().getName()));
        return Thread.currentThread().getName();
    }

}
