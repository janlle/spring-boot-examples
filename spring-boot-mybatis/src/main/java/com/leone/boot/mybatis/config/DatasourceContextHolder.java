package com.leone.boot.mybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>将线程池和线程绑定
 *
 * @author leone
 * @since 2021-01-20
 **/
public class DatasourceContextHolder {

    public static final Logger LOG = LoggerFactory.getLogger(DatasourceContextHolder.class);

    private static final ThreadLocal<DatasourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(-1);

    /**
     * 设置数据源类型
     *
     * @param type
     */
    public static void set(DatasourceType type) {
        CONTEXT_HOLDER.set(type);
    }

    /**
     * 获取数据源类型
     *
     * @return
     */
    public static DatasourceType get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 切换到读库master
     */
    public static void master() {
        set(DatasourceType.MASTER);
        LOG.info("切换到master");
    }

    /**
     * 切换到写库slave
     */
    public static void slave() {
        int index = COUNTER.getAndIncrement() % 2;
        if (COUNTER.get() > 9999) {
            COUNTER.set(-1);
        }
        if (index == 0) {
            set(DatasourceType.SLAVE1);
            LOG.info("切换到slave1");
        } else {
            set(DatasourceType.SLAVE2);
            LOG.info("切换到slave2");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            slave();
        }
    }

}