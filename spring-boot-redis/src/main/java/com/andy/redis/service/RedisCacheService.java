package com.andy.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p> 缓存
 *
 * @author Leone
 * @since 2018-08-11
 **/
@Slf4j
@Service
public class RedisCacheService {

    @Cacheable(value = "systemCatch")
    public String userCatch() {
        log.info("=====no catch====");
        return "当前系统时间:" + System.currentTimeMillis();
    }

}
