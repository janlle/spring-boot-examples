package com.leone.boot.data.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * @author leone
 * @since 2021-03-30
 **/
public class RedissonDemo {

    private static final Logger log = LoggerFactory.getLogger(RedissonDemo.class);

    private static final RedissonClient redisson;

    static {
        // 单例模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://exklin.local:6379");
        redisson = Redisson.create(config);
    }

    public static void main(String[] args) {
        stringTest();
    }

    public static void stringTest() {
        RMap<Object, Object> user = redisson.getMap("user");
        user.put("name", "james");
        user.put("age", "18");

        RMap<Object, Object> result = redisson.getMap("user");
        System.out.println(result.get("name"));
    }

    /**
     * 哨兵集群
     */
    public static void sentinelTest() {
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("master")
                .addSentinelAddress("127.0.0.1:26389")
                .addSentinelAddress("127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26369");
        RedissonClient redisson = Redisson.create(config);
    }

    /**
     * 集群模式
     */
    public static void clusterTest() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // cluster state scan interval in milliseconds
                .addNodeAddress("127.0.0.1:7000")
                .addNodeAddress("127.0.0.1:7001")
                .addNodeAddress("127.0.0.1:7002");
        RedissonClient redisson = Redisson.create(config);


    }

    /**
     * 主从模式
     */
    public static void masterSlave() {
        Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress("127.0.0.1:6379")
                .addSlaveAddress("127.0.0.1:6389")
                .addSlaveAddress("127.0.0.1:6399");
        RedissonClient redisson = Redisson.create(config);

    }


}
