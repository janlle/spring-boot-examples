package com.andy.cache.anno;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author Leone
 */
@Documented
@Inherited
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}