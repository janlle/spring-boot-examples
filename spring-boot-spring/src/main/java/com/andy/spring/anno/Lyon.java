package com.andy.spring.anno;

import java.lang.annotation.*;

/**
 * @author: Leone
 * @since: 2018-07-01 11:10
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Lyon {

    String value() default "";

    String [] versions() default {};

}
