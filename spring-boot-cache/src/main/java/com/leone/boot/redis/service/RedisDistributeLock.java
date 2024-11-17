package com.leone.boot.redis.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p> redis 分布式锁案例
 *
 * @author leone
 * @since 2024-11-04
 **/
@Slf4j
@Service
public class RedisDistributeLock {

    /**
     * 统一前缀
     */
    @Value("${redisson.lock.key}")
    private String prefix;

    @Resource
    private RedissonClient redissonClient;

    public void unsafe() {

    }

    public void safe() {

    }

    /**
     * 获取锁
     */
    private RLock getLock(String key, boolean fair) {
        String lockKey = prefix + ":" + key;
        return fair ? redissonClient.getFairLock(lockKey) : redissonClient.getLock(lockKey);
    }

    /**
     * 加锁
     */
    public RLock lock(String key, long lockTime, TimeUnit unit, boolean fair) {
        RLock lock = getLock(key, fair);
        if (lockTime > 0L) {
            // 获取锁,失败一直等待,直到获取锁,不支持自动续期
            lock.lock(lockTime, unit);
        } else {
            // 具有 Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
            lock.lock();
        }
        return lock;
    }

    /**
     * 加锁
     */
    public RLock tryLock(String key, long tryTime, long lockTime, TimeUnit unit, boolean fair) throws Exception {
        RLock lock = getLock(key, fair);
        boolean lockAcquired;
        if (lockTime > 0L) {
            // 尝试获取锁，获取不到超时异常,不支持自动续期
            lockAcquired = lock.tryLock(tryTime, lockTime, unit);
        } else {
            // 具有Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
            lockAcquired = lock.tryLock(tryTime, unit);
        }
        if (lockAcquired) {
            return lock;
        }
        return null;
    }

    /**
     * 释放锁
     */
    public void unLock(RLock lock) {
        if (lock != null && lock.isLocked()) {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.error("释放分布式锁异常", e);
            }
        }

    }

}
