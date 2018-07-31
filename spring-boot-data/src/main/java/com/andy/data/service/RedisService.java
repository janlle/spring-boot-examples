package com.andy.data.service;

import com.andy.data.config.RedisPrefix;
import com.andy.data.entity.User;
import com.andy.data.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: lyon
 * @since: 2018-05-11 19:41
 **/
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> operations;

    public long batchInsert(Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long start = System.currentTimeMillis();
        operations.set(RedisPrefix.cmsUserCatch(RandomUtil.getNum(6)), list.toString(), 3, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public long foreachInsert(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            User user = new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false);
//            redisTemplate.opsForValue().set(RedisPrefix.cmsUserCatch(RandomUtil.getNum(6)), user, 3, TimeUnit.SECONDS);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }


    public long setValue(Integer count) {
        log.info("setValue,count:{}", count);
//        User user = new User("james", "james", new Date(), 1000.0, new Date(), false);
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            User user = new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false);
            redisTemplate.opsForValue().set(RedisPrefix.webUserCatch(RandomUtil.getNum(6)), user, 3, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set(RedisPrefix.webUserCatch(RandomUtil.getNum(6)), "world");
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }


}
