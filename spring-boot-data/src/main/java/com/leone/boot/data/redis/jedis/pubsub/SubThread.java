package com.leone.boot.data.redis.jedis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p> 
 *
 * @author leone
 * @since 2019-01-31
 **/
public class SubThread extends Thread {

    private final JedisPool jedisPool;

    private final Subscriber subscriber = new Subscriber();

    private final String channel = "channel1";

    public SubThread(JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            // 取出一个连接
            jedis = jedisPool.getResource();
            // 通过subscribe 的api去订阅，入参是订阅者和频道名
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subscribe channel error, %s", e));
        } finally {
            jedis.close();
        }
    }
}