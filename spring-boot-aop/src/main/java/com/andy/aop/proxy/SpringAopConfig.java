package com.andy.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-21 00:16
 **/
@Slf4j
@Aspect//描述一个切面类，定义切面类的时候需要打上这个注解
@Component
public class SpringAopConfig {

    @Pointcut("execution(* com.andy.aop.service.*.*(..))")
    public void aop() {

    }

    @Around("aop()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕通知");
        point.proceed();
    }

    @Before("aop()")
    public void before() {
        System.out.println("前置通知");
    }

    @After("aop()")
    public void after() {
        System.out.println("后置通知");
    }

    @AfterReturning("aop()")
    public void afterReturning() {
        System.out.println("后置返回 ");
    }

    @AfterThrowing("aop()")
    public void afterThrowing() {
        System.out.println("后置异常");
    }

}
