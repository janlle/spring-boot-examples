package com.leone.boot.learn.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnSystemPropertiesCondition.class)
public @interface ConditionalOnSystemProperties {

    /**
     * 系统属性名称
     *
     * @return
     */
    String name();

    /**
     * 系统属性值
     *
     * @return
     */
    String value();

}
