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
public class RedisLikeExample {

    private static final String LIKE_PREFIX = "like:";
    private static final String USER_PREFIX = "user:";
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis(System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_port")), 3000);
        jedis.auth(System.getenv("redis_password"));
        System.out.println(jedis.ping());
        String postId = "100";
        for (int i = 0; i < 9; i++) {
            likePost(postId, "100" + i, jedis);
            Thread.sleep(new Random().nextInt(1000));
        }
        //System.out.println(jedis.zrange(LIKE_PREFIX + postId, 0, -1));
        System.out.println(getLikes(postId, jedis));
        jedis.close();
    }

    public static List<String> getLikes(String postId, Jedis jedis) {
        String key = LIKE_PREFIX + postId;
        return jedis.zrangeByScoreWithScores(key, "-inf", "+inf", 0, -1)
          //return jedis.zrevrangeByScoreWithScores(key, "+inf", "-inf", 0, -1)
          .stream()
          .map(e -> {
              System.out.println(e.getElement() + " " + e.getScore());
              return e.getElement();
          }).collect(Collectors.toList());
    }

    // 取消点赞
    public static void unlikePost(String postId, String userId, Jedis jedis) {
        String key = LIKE_PREFIX + postId;
        jedis.zrem(key, userId);
    }

    // 点赞
    public static void likePost(String postId, String userId, Jedis jedis) {
        //double now = System.currentTimeMillis() + random.nextDouble(1000);
        double now = random.nextDouble(1000);
        String key = LIKE_PREFIX + postId;
        jedis.zadd(key, now, userId);
    }

}
