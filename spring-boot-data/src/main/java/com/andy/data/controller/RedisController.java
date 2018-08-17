package com.andy.data.controller;

import com.andy.data.redis.RedisCacheService;
import com.andy.data.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lyon
 * @since: 2018-07-08
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
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.foreachInsert(count);
        return "批量插入" + count + "条数据一共用了:" + time + "豪秒！";
    }

    @GetMapping("/setValue")
    public String setValue(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.setValue(count);
        return "set" + count + "条数据一共用了:" + time + "豪秒！";
    }


    @GetMapping("/insert")
    public String insert(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = redisService.insert(count);
        return "set" + count + "条数据一共用了:" + time + "豪秒！";
    }


    @GetMapping("/catch")
    public String userCatch() {
        return redisCacheService.userCatch();
    }

}
