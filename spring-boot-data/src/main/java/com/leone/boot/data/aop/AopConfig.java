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
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(* com.leone.data.service.*.*(..))")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName();
        try {
            log.info("method name:" + methodName + ", params:" + Arrays.asList(point.getArgs()));
            long start = System.currentTimeMillis();
            point.proceed();
            long end = System.currentTimeMillis();
            long time = (end - start);
            log.info("time:{}", time + " ms!");
            return time;
        } catch (Throwable e) {
            log.info("message:{}", e.getMessage());
        }
        return -1;
    }


}
