package com.leone.boot.mvc.annocation;

import java.lang.annotation.*;

/**
 * @author leone
 * @since 2018-06-29
 **/

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerAnno {

    String name() default "hello";

    String value() default "";

    int max() default 0;

}
