package com.leone.boot.limited.intercepter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.leone.boot.limited.lock.LocalKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p> 基于aop本地缓存来方法调用限制
 *
 * @author leone
 * @since 2018-09-08
 **/
@Aspect
@Component
public class LockMethodInterceptor {

    @Autowired
    private LocalKeyGenerator localKeyGenerator;

    /**
     * 采用谷歌工具类的缓存工具
     */
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 1000 个
            .maximumSize(1000)
            // 设置写缓存后 3 秒钟过期
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();

    @Pointcut("execution(public * *(..)) && @annotation(com.leone.boot.limited.anno.LocalLock)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object interceptor(ProceedingJoinPoint point) {
        String key = localKeyGenerator.getLockKey(point);
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new IllegalArgumentException("请勿重复请求");
            }
            // 如果是第一次请求,就将 key 当前对象压入缓存中
            CACHES.put(key, key);
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throw new IllegalArgumentException("服务器异常");
        }
    }

}