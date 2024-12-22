package com.leone.boot.cache.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisPipelineExample {
    public static void main(String[] args) {

        try (Jedis jedis = new Jedis(System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000)) {
            jedis.auth(System.getenv("redis_password"));
            System.out.println(jedis.ping());
            Pipeline pipeline = jedis.pipelined();
            pipeline.set("foo", "bar");
            pipeline.get("foo");
            pipeline.incr("counter");
            List<Object> objects = pipeline.syncAndReturnAll();
            for (Object object : objects) {
                System.out.println(object);
            }
        }
    }
}
