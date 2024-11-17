package com.leone.boot.cache.redis.jedis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p> 
 *
 * @author leone
 * @since 2019-01-31
 **/
public class Publisher extends Thread {

    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 连接池中取出一个连接
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    // 从 channel1 的频道上推送消息
                    jedis.publish("channel1", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}