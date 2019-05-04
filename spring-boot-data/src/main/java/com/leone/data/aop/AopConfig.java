package com.leone.data.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-25
 **/
@Slf4j
@Aspect
@Component
public class AopConfig {

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
