package com.andy.spring.anno;

import java.lang.annotation.*;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 11:07
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Andy {

    String value() default "";

    String name() default "";

    int size() default 0;

}
