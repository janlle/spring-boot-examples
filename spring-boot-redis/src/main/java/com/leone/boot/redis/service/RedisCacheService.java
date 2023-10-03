package com.leone.boot.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p> 缓存
 *
 * @author leone
 * @since 2018-08-11
 **/
@Slf4j
@Service
public class RedisCacheService {

    @Cacheable(value = "systemCatch")
    public String userCatch() {
        System.out.println("no cache");
        return "当前系统时间:" + System.currentTimeMillis();
    }

}
