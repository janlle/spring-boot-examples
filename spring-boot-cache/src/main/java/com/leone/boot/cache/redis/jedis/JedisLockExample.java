package com.leone.boot.cache.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>使用redis的SETNX实现分布式锁，多个进程执行以下Redis命令：setnx lockKey lockValue
 * SETNX是将 key 的值设为 value，当且仅当 key 不存在。若给定的 key 已经存在，则 SETNX 不做任何动作。
 * <p>
 * 1.返回1，说明该进程获得锁，SETNX将键 lock.id 的值设置为锁的超时时间，当前时间 +加上锁的有效时间。
 * 2.返回0，说明其他进程已经获得了锁，进程不能进入临界区。进程可以在一个循环中不断地尝试 SETNX 操作，以获得锁。
 *
 * @author leone
 * @since 2019-07-19
 **/
public class JedisLockExample {

    private static final Logger log = LoggerFactory.getLogger(JedisLockExample.class);

    private static final JedisPool POOL = new JedisPool(new JedisPoolConfig(), System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000, System.getenv("redis_password"), 0);

    private static final CountDownLatch countDownLatch = new CountDownLatch(100);

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static int count;

    private static final String lockKey = "redisLock";

    public static void main(String[] args) throws Exception {
        System.out.println(tryLock());
        System.out.println(unlock());

        //safe();
        //unsafe();

    }

    public static void unsafe() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                executor();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count);
    }

    public static void safe() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                if (tryLock()) {
                    try {
                        executor();
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        unlock();
                    }
                }
            });
        }
        countDownLatch.await();
        System.out.println(count);
    }


    /**
     * 业务方法
     */
    public static void executor() {
        for (int i = 0; i < 10; i++) {
            count = count + 1;
        }
        //try {
        //    Thread.sleep(1);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
    }

    /**
     * 加锁
     */
    public static boolean tryLock() {
        long now = System.currentTimeMillis();
        // 锁过期时间
        int expire = 30000;
        boolean flag = POOL.getResource().setnx(lockKey, String.valueOf(now + expire)) == 1;
        if (!flag) {
            String timestamp = POOL.getResource().get(lockKey);
            if (Long.parseLong(timestamp) < now) {
                String oldTimestamp = POOL.getResource().setGet(lockKey, String.valueOf(now + expire));
                if (oldTimestamp.equals(timestamp)) {
                    POOL.getResource().expire(lockKey, 1000 * 3);
                    return true;
                }
            }
        }
        if (flag) {
            POOL.getResource().expire(lockKey, expire);
        }
        return flag;
    }

    /**
     * 解锁
     */
    public static boolean unlock() {
        return POOL.getResource().del(lockKey) == 1;
    }

}
