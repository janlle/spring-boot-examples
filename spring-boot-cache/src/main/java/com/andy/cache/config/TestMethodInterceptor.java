package com.andy.cache.config;

import com.andy.cache.anno.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
@Aspect
@Component
public class TestMethodInterceptor {

    @Pointcut("execution(public * *(..)) && @annotation(com.andy.cache.anno.LocalLock)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);

        System.out.println(localLock.key());

        System.out.println(localLock.expire());

        // 这里就可以处理其他业务逻辑和获取注解中的值

        return pjp.proceed();
    }


}
