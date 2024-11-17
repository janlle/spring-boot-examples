package com.leone.boot.cache.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2021-10-29
 **/
public class CacheUtil {

    private static RedisAdvancedClusterCommands<String, String> hw;
    private static RedisAdvancedClusterCommands<String, String> ts;

    private static RedisCommands<String, String> test;

    static {
        // hw = newCluster("10.251.104.9", 6379, null);
        ts = newCluster("10.251.98.15", 6380, null);
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


    public void delKey() {
        List<String> keys = hw.keys("promo_*");
        System.out.println(keys.size());
        for (String key : keys) {
            String val = hw.get(key);
            hw.del(key);
        }
    }


    public void listKey() {
        // List<String> l = Arrays.asList("FLINK:OA:D:20220419*");
        List<String> l = Arrays.asList("FLINK:OA:HOUR:*");
        for (String s : l) {
            List<String> ks = test.keys(s);
            if (!ks.isEmpty()) {
                System.err.println(s + " - " + ks.size());
                for (String k : ks) {
                    System.out.println(k + " - " + test.get(k));
                }
            }
        }
    }

    public void deleteTest() {
        List<String> keys = test.keys("FLINK:StoreCount:*");
        for (String key : keys) {
            System.out.println(key + "_" + test.del(key));
        }
    }

    // @Test
    public void keys() {
        List<String> l = Arrays.asList("FLINK:OA:D:20220403*", "FLINK:TAO:D:20220403*", "FLINK:TAP:D:20220403:*");
        for (int i = 0; i < l.size(); i++) {
            List<String> ks = test.keys(l.get(i));
            if (!ks.isEmpty()) {
                System.out.println(l.get(i) + " - " + ks.size());
                test.del(ks.toArray(new String[0]));
            }
        }
    }

    // @Test
    public void dataSync() throws Exception {
        List<String> keys = test.keys("M????????_??");
        System.out.println(keys.size());
        for (String key : keys) {
            if (test.get(key) == null) {
                // al get
                String data = test.get(key);
                // tx set
                test.set(key, data);
                System.out.println("sync " + key);
                Thread.sleep(10);
            }
        }
    }

}
