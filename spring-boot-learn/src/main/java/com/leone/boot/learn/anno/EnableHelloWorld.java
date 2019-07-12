package com.leone.boot.learn.anno;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import()
public @interface EnableHelloWorld {

}
