package com.leone.boot.cache.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p> redis pub sub 模式
 *
 * @author leone
 * @since 2019-01-29
 **/
public class RedisPubSubExample {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000, System.getenv("redis_password"), 0);
        System.out.printf("redis init %s%n", jedisPool.getResource().ping());
        String channel = "redis-channel";

        // 发布者
        new Publisher(jedisPool, channel).start();

        // 订阅者1
        new Subscribe(jedisPool, channel).start();

        // 订阅者2
        new Subscribe(jedisPool, channel).start();

    }


    static class Subscribe extends Thread {

        private final JedisPool jedisPool;
        private final String channel;
        private final RedisListener listener = new RedisListener();

        public Subscribe(JedisPool jedisPool, String channel) {
            this.channel = channel;
            this.jedisPool = jedisPool;
        }

        @Override
        public void run() {
            System.out.printf("subscribe %s%n", channel);
            try (Jedis jedis = jedisPool.getResource()) {
                // 通过subscribe 的api去订阅，入参是订阅者和频道名
                jedis.subscribe(listener, channel);
            } catch (Exception e) {
                System.out.printf("subscribe %s error: %s%n", channel, e);
            }
        }
    }

    static class Publisher extends Thread {

        private final JedisPool jedisPool;
        private final String channel;

        public Publisher(JedisPool jedisPool, String channel) {
            this.jedisPool = jedisPool;
            this.channel = channel;
        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line;
                try {
                    line = reader.readLine();
                    if (!"quit".equals(line)) {
                        jedisPool.getResource().publish(channel, line);
                    } else {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * <p>
     *
     * @author leone
     * @since 2019-01-31
     **/
    static class RedisListener extends JedisPubSub {
        /**
         * 收到消息会调用
         */
        @Override
        public void onMessage(String channel, String message) {
            System.out.printf(Thread.currentThread().getName() + " %s message: %s%n", channel, message);
        }

        /**
         * 订阅了会调用
         */
        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.printf(Thread.currentThread().getName() + " subscribe %s, subscribedChannels: %d%n", channel, subscribedChannels);
        }

        /**
         * 取消订阅会调用
         */
        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.printf(Thread.currentThread().getName() + " unsubscribe %s, subscribedChannels: %d%n", channel, subscribedChannels);

        }
    }

}
