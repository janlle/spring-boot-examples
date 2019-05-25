package com.leone.boot.aop.anno;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-25
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopAfterThrowing {
}
