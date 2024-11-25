package com.leone.boot.mvc.lock.redis;

import com.leone.boot.mvc.lock.DistributeLockConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁注解
 *
 * @author leone
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 锁的场景
     */
    String scene();

    /**
     * 加锁的key，优先取key()，如果没有，则取keyExpression()
     */
    String key() default DistributeLockConstant.DEFAULT_LOCK_KEY;

    /**
     * SPEL表达式:
     * <pre>
     *     #id
     *     #insertResult.id
     * </pre>
     */
    String keyExpression() default DistributeLockConstant.DEFAULT_LOCK_KEY;

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
