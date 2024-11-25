package com.leone.boot.data.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2021-03-30
 **/
public class LettuceFactory {

    private static final Logger log = LoggerFactory.getLogger(LettuceFactory.class);

    private RedisCommands<String, String> command;

    public static RedisCommands<String, String> newSingle(String host, int port, int db, String password) {
        RedisURI.Builder builder = RedisURI.builder()
                .withHost(host)
                .withPort(port)
                .withDatabase(db)
                .withTimeout(Duration.of(60, ChronoUnit.SECONDS));
        if (password != null) {
            builder.withPassword(password.toCharArray());
        }
        RedisClient client = RedisClient.create(builder.build());
        StatefulRedisConnection<String, String> connection = client.connect();
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

    public static RedisAdvancedClusterCommands<String, String> newCluster(List<String> hosts, String password) {
        RedisURI.Builder builder = RedisURI.builder()
                .withTimeout(Duration.of(60, ChronoUnit.SECONDS));

        for (String host : hosts) {
            String[] split = host.split(":");
            builder.withHost(split[0]);
            builder.withPort(Integer.parseInt(split[1]));
        }

        if (password != null) {
            builder.withPassword(password.toCharArray());
        }
        RedisClusterClient cluster = RedisClusterClient.create(builder.build());
        StatefulRedisClusterConnection<String, String> connection = cluster.connect();
        return connection.sync();
    }


    // 连接池
    private static GenericObjectPool<StatefulRedisConnection<String, String>> pool = null;

    public static synchronized GenericObjectPool<StatefulRedisConnection<String, String>> newSingleConnPool(String host, int port, String password) {
        if (pool == null) {
            RedisURI redisUri = RedisURI.builder()
                    .withHost(host)
                    .withPort(port)
                    .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                    .build();

            RedisClient redisClient = RedisClient.create(redisUri);

            GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig = new GenericObjectPoolConfig<>();
            poolConfig.setMinIdle(0);
            poolConfig.setMaxIdle(5);
            poolConfig.setMaxTotal(5);
            pool = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig);
        }
        return pool;
    }

    public static void main(String[] args) throws Exception {
        GenericObjectPool<StatefulRedisConnection<String, String>> pool = LettuceFactory.newSingleConnPool("localhost", 6379, null);
        StatefulRedisConnection<String, String> conn = pool.borrowObject();

        RedisCommands<String, String> command = conn.sync();
        command.set("a", "ab", SetArgs.Builder.ex(100));
        System.out.println(command.get("a"));

        pool.returnObject(conn);
    }

}
