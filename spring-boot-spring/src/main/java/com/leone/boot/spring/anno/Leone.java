package com.leone.boot.spring.anno;

import java.lang.annotation.*;

/**
 * @author leone
 * @since 2018-07-01
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Leone {

    String value() default "";

    String [] versions() default {};

}
