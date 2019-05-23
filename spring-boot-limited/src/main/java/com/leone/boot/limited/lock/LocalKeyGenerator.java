package com.leone.boot.limited.lock;

import com.leone.boot.limited.anno.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
@Component
public class LocalKeyGenerator implements LockKeyGenerator {

    /**
     * 生成本地key
     *
     * @param point
     * @return
     */
    @Override
    public String getLockKey(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = localLock.key();
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (int i = 0; i < point.getArgs().length; i++) {
            joiner.add(point.getArgs()[i].toString());
        }
        key = key + "-" + point.getTarget().getClass().getName() + "." + method.getName() + joiner.toString();
        return key;
    }

}
