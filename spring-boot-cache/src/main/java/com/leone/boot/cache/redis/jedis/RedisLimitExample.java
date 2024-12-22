package com.leone.boot.cache.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-22
 **/
public class RedisLimitExample {

    private static final Jedis jedis = new Jedis(System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000);
    private static final String key = "limit:test";
    private static final int limit = 3;

    public static void main(String[] args) throws InterruptedException {
        jedis.auth(System.getenv("redis_password"));
        System.out.println(jedis.ping());

        for (int i = 0; i < 9; i++) {
            Thread.sleep(new Random().nextLong(1200));
            //System.out.println(allowRequest(key));
            System.out.println(allowRequestLua(key));
        }

        jedis.close();
    }

    public static boolean allowRequestLua(String key) {
        long currentTime = System.currentTimeMillis();

        // 使用Lua脚本来确保原子性操作
        String luaScript = """
          local window_start = ARGV[1] - 3000
          redis.call('ZREMRANGEBYSCORE', KEYS[1], '-inf', window_start)
          local current_requests = redis.call('ZCARD', KEYS[1])
          if current_requests < tonumber(ARGV[2]) then
          redis.call('ZADD', KEYS[1], ARGV[1], ARGV[1])
              return 1
          else
              return 0
          end""";
        return (int) jedis.eval(luaScript, 1, key, String.valueOf(currentTime), String.valueOf(limit)) == 1;
    }

    public static boolean allowRequest(String key) {
        long currentTime = System.currentTimeMillis();

        // 窗口开始时间是当前时间减60s
        long windowStart = currentTime - 60 * 1000;

        // 删除窗口开始时间之前的所有数据
        jedis.zremrangeByScore(key, "-inf", String.valueOf(windowStart));

        //计算总请求数
        long currentRequests = jedis.zcard(key);

        //窗口足够则把当前请求加入
        if (currentRequests < limit) {
            jedis.zadd(key, currentTime, String.valueOf(currentTime));
            return true;
        }
        return false;
    }

}
