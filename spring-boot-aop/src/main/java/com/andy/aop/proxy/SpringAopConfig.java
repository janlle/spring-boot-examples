package com.andy.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 描述一个切面类
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-21 00:16
 **/
@Slf4j
@Aspect
@Component
public class SpringAopConfig {

    @Pointcut("execution(* com.andy.aop.service.*.*(..))")
    public void aop() {}

    @Around("aop()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            log.info("环绕通知开始,方法名:" + methodName + ",参数:" + Arrays.asList(point.getArgs()));
            result = point.proceed();
            log.info("环绕通知结束,方法名:" + methodName + ",参数:" + result);
        } catch (Throwable e) {
            log.info("发生异常...");
            e.printStackTrace();
        }
        return result;
    }


    @Before("aop()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("前置通知,方法名:" + methodName + ",参数:" + args);
    }


    @After("aop()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("后置通知,方法名:" + methodName + ",参数:" + args);
    }


    @AfterReturning( value="aop()", returning="result")
    public void afterReturning(JoinPoint joinPoint ,Object result){
        String methodName = joinPoint.getSignature().getName();
        log.info("后置返回,方法名:" + methodName + ",返回值:" + result);
    }



    @AfterThrowing(value = "aop()",throwing="ex")
    public void afterThrowing(JoinPoint joinPoint, Exception  ex){
        String methodName = joinPoint.getSignature().getName();
        log.info("后置异常通知 " + methodName + " exceptions " + ex);
    }

}
