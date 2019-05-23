package com.leone.boot.limited.anno;

import java.lang.annotation.*;


/**
 * <p>
 *
 * @author leone
 * @since 2018-09-29
 **/
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalLock {

    /**
     * key
     *
     * @return
     */
    String key() default "";

    /**
     * 过期时间默认秒
     *
     * @return
     */
    int expire() default 5;
}