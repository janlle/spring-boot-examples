package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.lock.DistributeLock;
import com.leone.boot.mvc.shiro.service.ShiroTokenService;
import com.leone.boot.mvc.shiro.service.UserHelper;
import org.apache.shiro.subject.Subject;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RedisTemplate<String, Integer> redisTemplate;

    private static final String databaseKey = "product.count";

    @DistributeLock(scene = "goods", keyExpression = "#count", key = "lock")
    @GetMapping("/reduce")
    public String reduce() throws Exception {
        Integer count = redisTemplate.opsForValue().get(databaseKey);
        if (count != null) {
            if (count > 0) {
                Thread.sleep(10000);
                redisTemplate.opsForValue().set(databaseKey, --count);
            }
        }
        return "success";
    }


}