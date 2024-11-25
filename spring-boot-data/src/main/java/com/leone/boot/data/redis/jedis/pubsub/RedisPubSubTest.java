package com.leone.boot.data.redis.jedis.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-29
 **/
public class RedisPubSubTest {


    public static void main(String[] args) {
        // 连接池配置对象 (config, host + port + timeout + password + db)
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.79.17", 6379, 5000, "123456", 1);

        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));

        // 订阅者
        SubThread subThread = new SubThread(jedisPool);
        subThread.start();

        // 发布者
        Publisher publisher = new Publisher(jedisPool);
        publisher.start();
    }


}
