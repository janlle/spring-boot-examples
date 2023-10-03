package com.leone.boot.data.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2018-08-11
 **/
@Service
public class RedisCacheService {

    @Cacheable(value = "userCatch")
    public String userCatch() {
        System.out.println("=====no catch====");
        return "当前系统时间:" + System.currentTimeMillis();
    }

}
