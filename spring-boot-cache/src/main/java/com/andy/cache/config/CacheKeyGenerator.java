package com.andy.cache.config;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p> key生成器
 *
 * @author Leone
 * @since 2018-09-08
 **/
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}