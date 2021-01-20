package com.leone.boot.mybatis.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2021-01-20
 **/
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.leone.boot.mybatis.config.Write) " +
            "&& (execution(* com.leone.boot.mybatis.service..*.select*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.find*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.load*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.leone.boot.mybatis.config.Write) " +
            "|| execution(* com.leone.boot.mybatis.service..*.insert*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.add*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.update*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.edit*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.delete*(..)) " +
            "|| execution(* com.leone.boot.mybatis.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DatasourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DatasourceContextHolder.master();
    }


//    static final String[] METHOD_PREFIX = new String[]{"get", "select", "find"};

    /**
     * 另一种写法：手动判断
     */
    /*@Before("execution(* com.leone.boot.mybatis.service.*.*(..))")
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        for (int i = 0; i < METHOD_PREFIX.length; i++) {
            if (methodName.startsWith(METHOD_PREFIX[i])) {
                DatasourceContextHolder.slave();
                return;
            }
        }
        DatasourceContextHolder.master();
    }*/


}