package com.leone.boot.data.mybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>将线程池和线程绑定
 * <p>
 * private static final AtomicInteger COUNTER = new AtomicInteger(-1);
 * if (Math.abs(COUNTER.getAndIncrement() % 2) == 0) {
 * set(DataSourceEnum.SLAVE1);
 * LOG.info("切换到slave1");
 * } else {
 * set(DataSourceEnum.SLAVE2);
 * LOG.info("切换到slave2");
 * }
 *
 * @author leone
 * @since 2021-01-20
 **/
public class DatasourceContextHolder {

    private static final Logger LOG = LoggerFactory.getLogger(DatasourceContextHolder.class);

    private static final ThreadLocal<DataSourceEnum> CONTEXT = new ThreadLocal<>();


    /**
     * 设置数据源类型
     */
    public static void set(DataSourceEnum type) {
        CONTEXT.set(type);
    }

    /**
     * 获取数据源类型
     */
    public static DataSourceEnum get() {
        return CONTEXT.get();
    }

    /**
     * clear
     */
    public static void clear() {
        CONTEXT.remove();
        LOG.info("DatasourceContextHolder.clear()");
    }


}