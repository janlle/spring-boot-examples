package com.leone.boot.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-24
 **/
@Aspect
public class WatchAspect {

    private final Logger log = LoggerFactory.getLogger(WatchAspect.class);

    @Around("@annotation(com.leone.boot.common.aop.Watch)")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature methodSignature)) {
            throw new IllegalArgumentException("target is not method");
        }
        Watch annotation = methodSignature.getMethod().getAnnotation(Watch.class);
        String name = annotation.name();
        try {
            log.info("time: {}", System.currentTimeMillis() - start);
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
