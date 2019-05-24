package com.leone.boot.mvc.anno;

import java.lang.annotation.*;

/**
 * @author leone
 * @since 2018-06-29
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerAnnotation {

    String name() default "name";

    String value() default "";

    int max() default 0;

}
