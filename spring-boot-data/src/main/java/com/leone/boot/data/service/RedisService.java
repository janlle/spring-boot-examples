package com.leone.boot.data.service;

import com.leone.boot.common.utils.EntityFactory;
import com.leone.boot.common.utils.RandomValue;
import com.leone.boot.data.config.RedisPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2018-08-11
 **/
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public long insert(Integer count) {
        log.info("insert:{}", count);
        for (int i = 0; i < count; i++) {
//            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1), 30, TimeUnit.SECONDS);
//            stringRedisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1).toString(), 1, TimeUnit.SECONDS);
//            valueOperations.set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1).toString(), 30, TimeUnit.SECONDS);
        }
        redisTemplate.opsForList().set(RedisPrefix.userCatch(RandomValue.randomNum(6)), 1L, EntityFactory.getUsers(1).toString());
        return 0;
    }

    public long batchInsert(Integer count) {
        log.info("insert:{}", count);
        redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomValue.randomNum(6)), EntityFactory.getUsers(count).toString(), 1, TimeUnit.SECONDS);
        return 0;
    }

    public long foreachInsert(Integer count) {
        log.info("insert:{}", count);
        redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomValue.randomNum(6)), EntityFactory.getUsers(count), 3, TimeUnit.SECONDS);
        return 0;
    }


    public long setValue(Integer count) {
        log.info("insert:{}", count);
        for (long i = 0; i < count; i++) {
//            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1), 3, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomValue.randomNum(6)), EntityFactory.getUsers(1), 3, TimeUnit.SECONDS);
        }
        return 0;
    }


}
