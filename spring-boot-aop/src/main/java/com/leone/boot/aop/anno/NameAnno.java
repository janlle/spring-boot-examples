package com.leone.boot.aop.anno;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-30
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameAnno {

    String name() default "";

    int age() default 0;

}
