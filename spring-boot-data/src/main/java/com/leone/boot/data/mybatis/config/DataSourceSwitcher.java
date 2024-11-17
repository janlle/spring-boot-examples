package com.leone.boot.data.mybatis.config;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-17
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSwitcher {

    /**
     * 默认数据源
     *
     * @return 读写库
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;

    /**
     * 清除
     *
     * @return 读库
     */
    boolean clear() default true;

}