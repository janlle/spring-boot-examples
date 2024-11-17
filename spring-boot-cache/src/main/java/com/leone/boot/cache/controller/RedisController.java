package com.leone.boot.cache.controller;

import com.leone.boot.cache.redis.RedisCacheService;
import com.leone.boot.cache.redis.RedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leone
 * @since 2018-07-08
 **/
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisCacheService redisCacheService;

    @GetMapping("/list")
    public String list(@RequestParam int count) {
        return "list: " + redisService.list(count) + " !";
    }

    @GetMapping("/value")
    public String value() {
        return "value: " + redisService.value() + " !";
    }

    @GetMapping("/set")
    public String set() {
        return "set " + redisService.set() + " !";
    }

    @GetMapping("/zSet")
    public String zSet() {
        return "zSet " + redisService.zSet() + " !";
    }

    @GetMapping("/hash")
    public String hash() {
        return "hash " + redisService.hash() + " !";
    }

    @GetMapping("/catch")
    public String userCatch() {
        return redisCacheService.userCatch();
    }


    @GetMapping("/publish")
    public String publish() {
        for (int i = 0; i < 10; i++) {
            stringRedisTemplate.convertAndSend("my-topic", "这是我发第 " + i + " 条的消息");
        }
        return "success";
    }

}
