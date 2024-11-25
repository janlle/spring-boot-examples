package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.lock.mysql.DatabaseLock;
import com.leone.boot.mvc.lock.redis.RedisLock;
import com.leone.boot.mvc.lock.zk.ZkLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-14
 **/
@RestController
@RequestMapping("/lock")
public class LockController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String databaseKey = "goods:count";
    private static final Random random = new Random();

    @RedisLock(scene = "goods", keyExpression = "#count", key = "lock")
    @GetMapping("/redis")
    public String redis() throws Exception {
        Object v = redisTemplate.opsForValue().get(databaseKey);
        if (v != null) {
            int count = Integer.parseInt(v.toString());
            Thread.sleep(random.nextInt(Math.abs(2)));
            redisTemplate.opsForValue().set(databaseKey, --count);
        }
        return "success";
    }

    @ZkLock(key = "goods", expireTime = 1, reentrant = true)
    @GetMapping("/zookeeper")
    public String zookeeper() throws Exception {
        Object v = redisTemplate.opsForValue().get(databaseKey);
        if (v != null) {
            int count = Integer.parseInt(v.toString());
            Thread.sleep(random.nextInt(Math.abs(2)));
            redisTemplate.opsForValue().set(databaseKey, --count);
        }
        return "success";
    }

    @DatabaseLock(key = "goods", expireTime = 1, reentrant = true)
    @GetMapping("/database")
    public String database() throws Exception {
        Object v = redisTemplate.opsForValue().get(databaseKey);
        if (v != null) {
            int count = Integer.parseInt(v.toString());
            Thread.sleep(1);
            redisTemplate.opsForValue().set(databaseKey, --count);
        }
        return "success";
    }


}