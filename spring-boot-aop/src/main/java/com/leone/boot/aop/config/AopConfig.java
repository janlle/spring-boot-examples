package com.leone.boot.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 描述一个切面类
 *
 * @author leone
 * @since 2018-06-21
 **/
@Aspect
@Component
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    /**
     * 1.通配符
     * [*]  匹配任意字符，但只能匹配一个元素
     * <p>
     * [..] 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
     * <p>
     * [+]  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
     * <p>
     * 切点表达式分为 修饰符  返回类型  包路径  方法名  参数
     * <p>
     * 2.切点表达式
     * <p>
     * 3.逻辑运算符
     * 表达式可由多个切点函数通过逻辑运算组成
     * ** && 与操作，求交集，也可以写成and
     * <p>
     * 例如 execution(* chop(..)) && target(Horseman)  表示Horseman及其子类的chop方法
     * <p>
     * ** || 或操作，任一表达式成立即为true，也可以写成 or
     * <p>
     * 例如 execution(* chop(..)) || args(String)  表示名称为chop的方法或者有一个String型参数的方法
     * <p>
     * ** ! 非操作，表达式为false则结果为true，也可以写成 not
     * <p>
     * 例如 execution(* chop(..)) and !args(String)  表示名称为chop的方法但是不能是只有一个String型参数的方法
     */
    @Pointcut("execution(* com.leone.boot.aop.service.*.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知在 target 开始和结束执行
     *
     * @param point
     * @return
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        String methodName = point.getSignature().getName();
        log.info("around method name: {} params: {}", methodName, Arrays.asList(point.getArgs()));
        try {
            log.info("around end time: {}", (System.currentTimeMillis() - start) + " ms!");
            return point.proceed();
        } catch (Throwable e) {
            log.error("message: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 前置通知在 target 前执行
     *
     * @param joinPoint
     */
    // @Before("@annotation(com.leone.boot.aop.anno.AopBefore)")
    // @Before("within(com.leone.boot.aop.controller.*)")
    // @Before("@within(org.springframework.web.bind.annotation.RestController)")
    // @Before("target(com.leone.boot.aop.controller.UserController)")
    @Before("@target(com.leone.boot.aop.anno.ClassAop) && @annotation(com.leone.boot.aop.anno.AopBefore)")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("before inform method name: {} param: {}", methodName, args);
    }


    /**
     * 后置通知在target后执行
     *
     * @param joinPoint
     */
    @After("@args(org.springframework.stereotype.Component) && execution(* com.leone.boot.aop.controller.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.info("after inform method name : {} param: {}", methodName, args);
    }

    /**
     * 后置返回在target返回后执行
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "within(com.leone.boot.aop.controller.*)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("afterReturning inform method name: {} return value: {}", methodName, result);
    }

    /**
     * 后置异常通知在target异常后执行
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "args(com.leone.boot.common.entity.User) && execution(* com.leone.boot.aop.controller.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.info("afterThrowing inform method name: {} exceptions: {}" + methodName, ex);
    }


}
