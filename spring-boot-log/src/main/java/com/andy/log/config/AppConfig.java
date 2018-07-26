package com.andy.log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author: Mr.lyon
 * @createBy: 2018-06-12 23:13
 **/
@EnableAsync
@Configuration
public class AppConfig {

    //线程池初始数量大小
    private static final int CORE_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 10;


    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();
        return executor;
    }



}
