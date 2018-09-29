package com.andy.cache.anno;

import java.lang.annotation.*;


/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-29
 **/
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalLock {

    String key() default "";

    int expire() default 5;
}