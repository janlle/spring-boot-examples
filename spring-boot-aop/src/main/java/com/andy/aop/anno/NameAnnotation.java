package com.andy.aop.anno;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameAnnotation {

    String name() default "";

    int age() default 0;



}
