package com.andy.aop.proxy;

import com.andy.aop.anno.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 描述一个切面类
 *
 * @author Leone
 * @since 2018-06-21
 **/
@Slf4j
@Aspect
@Component
public class SpringAopConfig {

    @Pointcut("execution(* com.andy.aop.service.*.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知在target开始和结束执行
     *
     * @param point
     * @return
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            log.info("环绕通知开始,方法名:" + methodName + ",参数:" + Arrays.asList(point.getArgs()));
            result = point.proceed();
            log.info("环绕通知结束,方法名:" + methodName + ",参数:" + Arrays.asList(point.getArgs()));
        } catch (Throwable e) {
            log.info("发生异常...");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 前置通知在target前执行
     *
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("前置通知,方法名:" + methodName + ",参数:" + args);
    }

    /**
     * 后置通知在target后执行
     *
     * @param joinPoint
     */
    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("后置通知,方法名:" + methodName + ",参数:" + args);
    }

    /**
     * 后置返回在target返回后执行
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("后置返回,方法名:" + methodName + ",返回值:" + result);
    }

    /**
     * 后置异常通知在target异常后执行
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.info("后置异常通知 " + methodName + " exceptions " + ex);
    }


    @Around("@annotation(com.andy.aop.anno.SystemLog)")
    public Object customerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        SystemLog systemLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SystemLog.class);
        System.out.println("description" + systemLog.description());
        System.out.println("name" + systemLog.name());
        System.out.println("value" + systemLog.value());
        return null;
    }


}
