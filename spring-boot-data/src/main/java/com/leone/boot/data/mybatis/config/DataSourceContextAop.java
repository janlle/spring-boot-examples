package com.leone.boot.data.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * Pointcut("!@annotation(com.leone.boot.mybatis.config.Write) " +
 * "&& (execution(* com.leone.boot.mybatis.service..*.select*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.find*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.load*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.get*(..)))")
 * public void readPointcut() {}
 * <p>
 * Pointcut("@annotation(com.leone.boot.mybatis.config.Write) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.insert*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.add*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.update*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.edit*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.delete*(..)) " +
 * "|| execution(* com.leone.boot.mybatis.service..*.remove*(..))")
 * public void writePointcut() {}
 *
 * @author leone
 * @since 2024-11-17
 **/
@Slf4j
@Aspect
@Component
@Order(value = 1)
public class DataSourceContextAop {

    @Around("@annotation(com.leone.boot.data.mybatis.config.DataSourceSwitcher)")
    public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
        boolean clear = false;
        try {
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            DataSourceSwitcher dataSourceSwitcher = method.getAnnotation(DataSourceSwitcher.class);
            clear = dataSourceSwitcher.clear();
            DatasourceContextHolder.set(dataSourceSwitcher.value());
            log.info("数据源切换至：{}", dataSourceSwitcher.value().getDataSourceName());
            return pjp.proceed();
        } finally {
            if (clear) {
                DatasourceContextHolder.clear();
            }
        }
    }

}