package com.leone.boot.spring.anno;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;
import org.springframework.context.annotation.Import;

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
