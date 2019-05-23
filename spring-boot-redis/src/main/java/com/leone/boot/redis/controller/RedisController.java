package com.leone.boot.redis.controller;

import com.leone.boot.redis.service.RedisCacheService;
import com.leone.boot.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
