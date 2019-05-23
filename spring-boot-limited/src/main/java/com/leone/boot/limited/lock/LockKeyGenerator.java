package com.leone.boot.limited.lock;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p> key生成器
 *
 * @author leone
 * @since 2018-09-08
 **/
public interface LockKeyGenerator {

    /**
     * 获取 AOP 参数,生成指定缓存 Key
     *
     * @param point
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint point);

}