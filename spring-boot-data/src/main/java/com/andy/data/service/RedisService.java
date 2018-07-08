package com.andy.data.service;

import com.andy.data.config.RedisPrefix;
import com.andy.data.entity.User;
import com.andy.data.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.lyon
 * @createBy: 2018-05-11 19:41
 **/
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public long insertList(Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long start = System.currentTimeMillis();
        redisTemplate.opsForValue().set(RedisPrefix.cmsUserCatch(RandomUtil.getNum(6)), list, 3, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public long insertForeach(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            User user = new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false);
            redisTemplate.opsForValue().set(RedisPrefix.cmsUserCatch(RandomUtil.getNum(6)), user, 3, TimeUnit.SECONDS);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

}
