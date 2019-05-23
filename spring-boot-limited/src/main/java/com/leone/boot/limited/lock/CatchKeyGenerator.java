package com.leone.boot.limited.lock;

import com.leone.boot.limited.anno.CacheLock;
import com.leone.boot.limited.anno.CacheParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p> key 生成规则实现
 *
 * @author leone
 * @since 2018-09-08
 **/
@Component
public class CatchKeyGenerator implements LockKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        CacheLock lockAnnotation = method.getAnnotation(CacheLock.class);
        final Object[] args = point.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        // 默认解析方法里面带CacheParam注解的属性,如果没有尝试着解析实体对象中的CacheParam注解属性
        for (int i = 0; i < parameters.length; i++) {
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(lockAnnotation.delimiter()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())) {
            // CacheLock注解的方法参数没有CacheParam注解，则迭代解析参数实体中的CacheParam注解属性
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return lockAnnotation.prefix() + builder.toString();
    }

}