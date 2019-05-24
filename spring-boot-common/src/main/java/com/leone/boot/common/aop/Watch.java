package com.leone.boot.common.aop;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-24
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Watch {

    String name() default "";

}
