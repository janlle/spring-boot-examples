package com.leone.redis.controller;

import com.leone.redis.service.RedisCacheService;
import com.leone.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Leone
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
    public String list() {
        return "list: " + redisService.list() + " !";
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
