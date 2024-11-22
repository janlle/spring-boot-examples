package com.leone.boot.mvc.lock;

import com.leone.boot.mvc.lock.zk.ZkLockAspect;
import org.apache.curator.framework.CuratorFramework;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leone
 */
@Configuration
public class DistributeLockConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DistributeLockAspect distributeLockAspect(RedissonClient redisson) {
        return new DistributeLockAspect(redisson);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZkLockAspect zkLockAspect(CuratorFramework client) {
        return new ZkLockAspect(client);
    }

}
