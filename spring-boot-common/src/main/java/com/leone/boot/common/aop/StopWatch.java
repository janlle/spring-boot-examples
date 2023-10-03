package com.leone.boot.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-24
 **/
@Aspect
public class StopWatch {

    private final Logger log = LoggerFactory.getLogger(StopWatch.class);

    @Around("@annotation(com.leone.boot.common.aop.Watch)")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("target is not method");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
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
