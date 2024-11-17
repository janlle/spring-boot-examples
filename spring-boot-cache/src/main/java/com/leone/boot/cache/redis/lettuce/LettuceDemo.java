package com.leone.boot.cache.redis.lettuce;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
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
public class LettuceDemo {

    private static final Logger log = LoggerFactory.getLogger(LettuceDemo.class);

    private RedisCommands<String, String> command;

    public static RedisCommands<String, String> newSingle(String host, int port, int db, String password) {
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

        // 2、创建Redis客户端
        RedisClient client = RedisClient.create(builder.build());

        // 3、连接
        StatefulRedisConnection<String, String> connection = client.connect();

        // 4、创建命令操作对象，同步（sync）、异步（async）、反应式（reactive）
        return connection.sync();
    }

    public static RedisAdvancedClusterCommands<String, String> newCluster(String host, int port, String password) {
        RedisURI.Builder builder = RedisURI.builder()
                .withHost(host)
                .withPort(port)
                .withTimeout(Duration.of(60, ChronoUnit.SECONDS));

        if (password != null) {
            builder.withPassword(password.toCharArray());
        }

        RedisClusterClient cluster = RedisClusterClient.create(builder.build());

        StatefulRedisClusterConnection<String, String> connection = cluster.connect();
        return connection.sync();
    }

    public void init() {
        command = newSingle("localhost", 6379, 0, null);
    }

    // @Test
    public void delKey() {
        command.del("FLINK:OA:HOUR:010");
    }

    // @Test
    public void scanKey() {
        KeyScanCursor<String> scan = command.scan(ScanArgs.Builder.matches("FLINK:D:*").limit(100000));
        List<String> keys = scan.getKeys();
        System.out.println(keys.size());
        for (String s : keys) {
            System.out.println(s);
        }
    }

    public void keys() {
        List<String> keys = command.keys("FLINK:USAGE:M:2021_11*");
        System.out.println(keys.size());
        for (String s : keys) {
            System.out.println(s + "\t\t\t" + command.get(s));
        }
    }


    /**
     * string 类型测试
     */
    // @Test
    public void stringTest() {
        // set
        command.set("aa", "bb");
        // get
        String value = command.get("key");
        log.info(value);

    }

    /**
     * set测试
     */
    // @Test
    public void hashTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "james");
        map.put("age", "18");
        // set
        command.hset("map", map);

        // get
        Map<String, String> resultMap = command.hgetall("map");
        log.info("{}", resultMap);
    }

    /**
     * 列表测试
     */
    // @Test
    public void listTest() {
        // push
        command.lpush("fans", "a", "b", "c");

        // push
        command.lpushx("fans", "b");

        // pop
        command.rpop("fans");
    }

    /**
     * 列表测试
     */
    // @Test
    public void setTest() {
        // push
        command.sadd("set", "a", "b", "c");
        // pop
        String s = command.spop("set");

        log.info("{}", s);
    }

    // @Test
    public void sortedSetTest() {
        // add
        command.zadd("sortedSet", 0.4, "a");

        // card
        Long s = command.zcard("sortedSet");

        log.info("{}", s);
    }


}
