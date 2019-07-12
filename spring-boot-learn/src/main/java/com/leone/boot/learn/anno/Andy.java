package com.leone.boot.learn.anno;

import java.lang.annotation.*;

/**
 * @author leone
 * @since 2018-07-01
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Andy {

    String value() default "";

}
