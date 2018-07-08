package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import com.andy.data.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class MyBatisService {

    @Autowired
    private UserMapper userMapper;

    public long insertList(Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long start = System.currentTimeMillis();
        userMapper.insertList(list);
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public long insertForeach(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userMapper.insert(new User("james" + i, "james" + i, new Date(), 1000.0 + i, new Date(), false));
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

}
