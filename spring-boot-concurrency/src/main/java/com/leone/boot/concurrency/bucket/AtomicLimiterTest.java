package com.leone.boot.concurrency.bucket;



import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-09
 **/
public class AtomicLimiterTest {

    /**
     * 计数器限流算法（比较暴力/超出直接拒绝）
     * Atomic，限制总数
     */
    private static final AtomicInteger atomic = new AtomicInteger(0);

    private void atomicLimiter() {
        // 最大支持 3 个
        if (atomic.get() >= 3) {
            System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - " + "拒绝...");
        } else {
            try {
                atomic.incrementAndGet();
                // 处理核心逻辑
                System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - " + "通过...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                atomic.decrementAndGet();
            }
        }
    }


    public static void main(String[] args) throws Exception {

    }

    
    public void testAtomic() throws InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(this::atomicLimiter);
        }
        TimeUnit.SECONDS.sleep(5);
    }
}