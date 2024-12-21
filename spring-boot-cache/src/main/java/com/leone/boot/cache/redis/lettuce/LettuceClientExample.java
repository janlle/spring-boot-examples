package com.leone.boot.cache.redis.lettuce;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2021-03-30
 **/
public class LettuceClientExample {

    private static final Logger log = LoggerFactory.getLogger(LettuceClientExample.class);

    private static RedisClient redisClient;
    private static RedisClusterClient clusterClient;

    private static GenericObjectPool<StatefulRedisConnection<String, String>> pool;

    public static void init() {
        redisClient = newSingle(System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_host")), 0, System.getenv("redis_password"));
        clusterClient = newCluster(System.getenv("redis_host"), Integer.parseInt(System.getenv("redis_host")), System.getenv("redis_password"));

    }

    /**
     * 单节点模式
     */
    public static RedisClient newSingle(String host, int port, int db, String password) {
        // redis://[password@]host[:port][/databaseNumber][?[timeout=timeout[d|h|m|s|ms|us|ns]]
        // RedisClient client = RedisClient.create("redis://127.0.0.1:6379/0?timeout=5s");

        // 1、创建连接信息
        RedisURI.Builder builder = RedisURI.builder()
          .withHost(host)
          .withPort(port)
          .withDatabase(db)
          .withTimeout(Duration.of(60, ChronoUnit.SECONDS));
        if (password != null) {
            builder.withPassword(password.toCharArray());
        }
        // 4、创建命令操作对象，同步（sync）、异步（async）、反应式（reactive）
        return RedisClient.create(builder.build());
    }

    /**
     * 集群模式
     */
    public static RedisClusterClient newCluster(String host, int port, String password) {
        RedisURI.Builder builder = RedisURI.builder()
          .withHost(host)
          .withPort(port)
          .withTimeout(Duration.of(10, ChronoUnit.SECONDS));
        if (password != null) {
            builder.withPassword(password.toCharArray());
        }
        return RedisClusterClient.create(builder.build());
    }


    /**
     * 连接池方式
     */
    public static GenericObjectPool<StatefulRedisConnection<String, String>> newSingleConnPool(String host, int port, String password) {
        if (pool == null) {
            RedisClient client = newSingle(host, port, 0, password);
            GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig = new GenericObjectPoolConfig<>();
            poolConfig.setMinIdle(0);
            poolConfig.setMaxIdle(5);
            poolConfig.setMaxTotal(5);
            pool = ConnectionPoolSupport.createGenericObjectPool(client::connect, poolConfig);
        }
        return pool;
    }


    public static void scanKey() {
        KeyScanCursor<String> scan = clusterClient.connect().sync().scan(ScanArgs.Builder.matches("FLINK*").limit(1000));
        List<String> keys = scan.getKeys();
        System.out.println(keys.size());
        for (String s : keys) {
            System.out.println(s);
        }
    }

    public static void keys() {
        List<String> keys = redisClient.connect().sync().keys("FLINK*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println(key + "\t" + redisClient.connect().sync().get(key));
        }
    }


    /**
     * string 类型测试
     */
    public static void stringTest() {
        redisClient.connect().sync().set("a", "a");
        String value = redisClient.connect().sync().get("a");
        log.info(value);
    }

    /**
     * set测试
     */
    public static void hashTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "james");
        map.put("age", "18");
        redisClient.connect().sync().hset("user", map);
        Map<String, String> user = redisClient.connect().sync().hgetall("user");
        log.info("{}", user);
    }

    /**
     * 列表测试
     */
    public static void listTest() {
        // push
        redisClient.connect().sync().lpush("fans", "a", "b", "c");
        // push
        redisClient.connect().sync().lpushx("fans", "b");
        // pop
        redisClient.connect().sync().rpop("fans");
    }

    /**
     * 列表测试
     */
    public static void setTest() {
        // push
        redisClient.connect().sync().sadd("set", "a", "b", "c");
        // pop
        String s = redisClient.connect().sync().spop("set");
        log.info("{}", s);
    }

    /**
     * sortedSet
     */
    public static void sortedSetTest() {
        // add
        redisClient.connect().sync().zadd("sortedSet", 0.4, "a");
        // card
        Long s = redisClient.connect().sync().zcard("sortedSet");
        log.info("{}", s);
    }


}
