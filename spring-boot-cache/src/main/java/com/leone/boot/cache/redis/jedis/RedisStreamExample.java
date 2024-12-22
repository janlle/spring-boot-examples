package com.leone.boot.cache.redis.jedis;

import redis.clients.jedis.*;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.resps.StreamEntry;

import java.util.List;
import java.util.Map;

/**
 * <p> redis pub sub 模式
 *
 * @author leone
 * @since 2019-01-29
 **/
public class RedisStreamExample {

    private static final String STREAM_KEY = "stream:test";
    private static final String GROUP_NAME = "g1";
    private static final String CONSUMER_NAME = "c1";

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000, System.getenv("redis_password"), 0)) {
            System.out.printf("%s%n", jedisPool.getResource().ping());

            // 生产者
            new Thread(() -> {
                try {
                    Jedis jedis = jedisPool.getResource();
                    for (int i = 0; i < 9; i++) {
                        Map<String, String> map = Map.of("message", "hello " + i);
                        StreamEntryID id = jedis.xadd(STREAM_KEY, new StreamEntryID(System.currentTimeMillis(), i), map);
                        System.out.printf("发送消息 %s id:%s%n", map, id);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            //jedisPool.getResource().xgroupCreate(STREAM_KEY, GROUP_NAME, new StreamEntryID(), true);

            Jedis jedis = jedisPool.getResource();
            // 消费消息
            new Thread(() -> {
                while (true) {
                    Map<String, StreamEntryID> entry = Map.of(STREAM_KEY, StreamEntryID.XREADGROUP_UNDELIVERED_ENTRY);
                    List<Map.Entry<String, List<StreamEntry>>> list = jedis.xreadGroup(GROUP_NAME, CONSUMER_NAME, XReadGroupParams.xReadGroupParams().block(1000), entry);
                    if (list != null) {
                        for (Map.Entry<String, List<StreamEntry>> listEntry : list) {
                            System.err.printf("%s key: %s value: %s%n", Thread.currentThread().getName(), listEntry.getKey(), listEntry.getValue());
                            //System.out.printf("%s id:%s message:%s%n", Thread.currentThread().getName(), listEntry.getValue().get(0).getID(), new Gson().toJson(listEntry.getValue().get(0).getFields()));
                        }
                    }
                }
            }).start();
        }
    }

}
