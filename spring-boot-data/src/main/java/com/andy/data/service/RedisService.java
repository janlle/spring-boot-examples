package com.andy.data.service;

import com.andy.data.config.RedisPrefix;
import com.andy.data.util.EntityFactory;
import com.andy.data.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-08-11
 **/
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ValueOperations<String, String> operations;

    @Autowired
    private HashOperations hashOperations;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private ListOperations<String, String> listOperations;

    @Autowired
    private SetOperations setOperations;

    @Autowired
    private ZSetOperations zSetOperations;


    public long insert(Integer count) {
        log.info("insert:{}", count);
        for (int i = 0; i < count; i++) {
//            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1), 30, TimeUnit.SECONDS);
//            stringRedisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1).toString(), 1, TimeUnit.SECONDS);
//            valueOperations.set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1).toString(), 30, TimeUnit.SECONDS);
        }
        listOperations.set(RedisPrefix.userCatch(RandomUtil.getNum(6)), 1L, EntityFactory.getUsers(1).toString());
        return 0;
    }

    public long batchInsert(Integer count) {
        log.info("insert:{}", count);
        operations.set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(count).toString(), 1, TimeUnit.SECONDS);
        return 0;
    }

    public long foreachInsert(Integer count) {
        log.info("insert:{}", count);
        redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(count), 3, TimeUnit.SECONDS);
        return 0;
    }


    public long setValue(Integer count) {
        log.info("insert:{}", count);
        for (long i = 0; i < count; i++) {
//            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1), 3, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(RedisPrefix.userCatch(RandomUtil.getNum(6)), EntityFactory.getUsers(1), 3, TimeUnit.SECONDS);
        }
        return 0;
    }


}
