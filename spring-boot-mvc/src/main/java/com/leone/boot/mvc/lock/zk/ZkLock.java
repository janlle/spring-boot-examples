package com.leone.boot.mvc.lock.zk;

import com.leone.boot.mvc.lock.DistributeLockConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> zk分布式锁
 *
 * @author leone
 * @since 2024-11-22
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZkLock {

    /**
     * 是否可重入
     */
    boolean reentrant() default false;

    /**
     * 锁的场景
     */
    String keyPrefix() default DistributeLockConstant.LOCK_PREFIX;

    /**
     * 加锁的key
     */
    String key() default DistributeLockConstant.NONE_KEY;

    /**
     * 超时时间，毫秒
     * 默认情况下不设置超时时间，会自动续期
     */
    int expireTime() default DistributeLockConstant.DEFAULT_EXPIRE_TIME;

    /**
     * 加锁等待时长，毫秒
     * 默认情况下不设置等待时长，不做等待
     */
    int waitTime() default DistributeLockConstant.DEFAULT_WAIT_TIME;

}
