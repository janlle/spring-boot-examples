package com.leone.limited.arithmetic.tokenBucket;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
public class TokenBucketTest {

    private static RateLimiter limiter = RateLimiter.create(5);

    public static void exec() {
        limiter.acquire(1);
        try {
            // 处理核心逻辑
            TimeUnit.SECONDS.sleep(1);
            System.out.println(System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            new Thread(TokenBucketTest::exec).start();
        }
    }


}
