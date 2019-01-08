package com.andy.redis.controller;

import com.andy.redis.service.RedisCacheService;
import com.andy.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leone
 * @since 2018-07-08
 **/
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping("/batchInsert")
    public String insertList(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.batchInsert(count);
        return "foreach batch " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.foreachInsert(count);
        return "foreach batch " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/setValue")
    public String setValue(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.setValue(count);
        return "save " + count + " expenditure:" + time + " ms!";
    }


    @GetMapping("/insert")
    public String insert(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.insert(count);
        return "save " + count + " expenditure:" + time + " ms!";
    }


    @GetMapping("/catch")
    public String userCatch() {
        return redisCacheService.userCatch();
    }

}
