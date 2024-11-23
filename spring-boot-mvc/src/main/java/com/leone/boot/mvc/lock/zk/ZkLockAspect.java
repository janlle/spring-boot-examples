package com.leone.boot.mvc.lock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * zk分布式锁切面
 *
 * @author leone
 */
@Aspect
@Component
@ConditionalOnBean(CuratorFramework.class)
public class ZkLockAspect {

    private static final Logger log = LoggerFactory.getLogger(ZkLockAspect.class);

    private final ConcurrentMap<Thread, InterProcessLock> reentrantMap = new ConcurrentHashMap<>();

    private CuratorFramework client;

    public ZkLockAspect(CuratorFramework client) {
        this.client = client;
    }

    @Around("@annotation(com.leone.boot.mvc.lock.zk.ZkLock)")
    public Object process(ProceedingJoinPoint jointPoint) throws Exception {
        Method method = ((MethodSignature) jointPoint.getSignature()).getMethod();
        ZkLock annotation = method.getAnnotation(ZkLock.class);
        String key = annotation.key();
        int time = annotation.expireTime();

        InterProcessLock lock;
        Thread thread = Thread.currentThread();
        if (annotation.reentrant()) {
            //可重入
            lock = reentrantMap.get(thread);
            if (ObjectUtils.isEmpty(lock)) {
                lock = new InterProcessMutex(client, annotation.keyPrefix() + key);
                reentrantMap.put(thread, lock);
            }
        } else {
            //不可重入
            lock = new InterProcessSemaphoreMutex(client, annotation.keyPrefix() + key);
        }
        // 加锁
        log.info("获取到排他锁: {}", thread.getName());
        lock.acquire(time, TimeUnit.MILLISECONDS);
        //执行代码
        Object proceed = null;
        try {
            proceed = jointPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            lock.release();
            if (annotation.reentrant()) {
                InterProcessMutex lock1 = (InterProcessMutex) reentrantMap.get(thread);
                if (!lock1.isOwnedByCurrentThread()) {
                    reentrantMap.remove(thread);
                }
            }
            log.info("释放排他锁: {}", thread.getName());
        }
        return proceed;
    }


}
