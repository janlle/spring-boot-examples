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

    private final static Logger log = LoggerFactory.getLogger(JedisLockExample.class);

    private static final JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 3000);

    private static final Jedis jedis = jedisPool.getResource();

    private final CountDownLatch countDownLatch = new CountDownLatch(200);

    private static final String lockKey = "redisLock";

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final JedisLockExample lock = new JedisLockExample();

    private static int index;

    public static void main(String[] args) {
        lock.safe();
        //lock.unsafe();
    }


    public void unsafe() {
        for (int i = 0; i < 200; i++) {
            executorService.execute(() -> {
                executor();
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }

    public void safe() {
        for (int i = 0; i < 200; i++) {
            executorService.execute(() -> {
                if (lock.tryLock()) {
                    try {
                        executor();
                        countDownLatch.countDown();
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }


    /**
     * 业务方法
     */
    public static void executor() {
        for (int i = 0; i < 100; i++) {
            index++;
        }
    }

    /**
     * 加锁
     */
    public boolean tryLock() {
        long now = System.currentTimeMillis();
        // 锁过期时间
        int expire = 30000;
        boolean flag = jedis.setnx(lockKey, String.valueOf(now + expire)) == 1;

        if (!flag) {
            String timestamp = jedis.get(lockKey);
            if (Long.parseLong(timestamp) < now) {
                String oldTimestamp = jedis.setGet(lockKey, String.valueOf(now + expire));
                if (oldTimestamp.equals(timestamp)) {
                    jedis.expire(lockKey, expire);
                    return true;
                }
            }
        }
        if (flag) {
            jedis.expire(lockKey, expire);
        }
        return flag;
    }

    /**
     * 解锁
     */
    public boolean unlock() {
        return jedis.del(lockKey) == 1;
    }

}
