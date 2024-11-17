package com.leone.boot.data.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-25
 **/
@Aspect
@Component
public class MethodWatch {

    private static final Logger log = LoggerFactory.getLogger(MethodWatch.class);

    @Pointcut("execution(* com.leone.data.service.*.*(..))")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName();
        try {
            log.info("method: {}, params: {}", methodName, Arrays.asList(point.getArgs()));
            long start = System.currentTimeMillis();
            point.proceed();
            long time = (System.currentTimeMillis() - start);
            log.info("time: {} ms!", time);
            return time;
        } catch (Throwable e) {
            log.error("message:{}", e.getMessage());
        }
        return -1;
    }


}
