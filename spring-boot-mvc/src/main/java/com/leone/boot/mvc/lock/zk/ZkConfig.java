package com.leone.boot.mvc.lock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-22
 **/
@Configuration
public class ZkConfig {

    private final static Logger LOG = LoggerFactory.getLogger(ZkConfig.class);

    @Value("${zookeeper.host:localhost}")
    private String host;

    @Value("${zookeeper.port:2181}")
    private Integer port;

    @Bean
    @ConditionalOnProperty(prefix = "zookeeper", value = "enabled", matchIfMissing = false, havingValue = "true")
    public CuratorFramework curatorFramework() throws IOException {
        // 初始化连接
        CuratorFramework build = CuratorFrameworkFactory.builder()
          .connectString(host + ":" + port)
          .sessionTimeoutMs(5000)
          .connectionTimeoutMs(5000)
          .retryPolicy(new ExponentialBackoffRetry(1000, 3))
          .build();
        build.start();
        return build;
    }

}
