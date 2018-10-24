package com.andy.mvc.service;

import com.andy.mvc.dao.UserRepository;
import com.andy.mvc.dao.mapper.UserMapper;
import com.andy.mvc.entity.User;
import com.andy.mvc.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-24
 **/
@Slf4j
@Service
public class UserService {

    private final Map<String, User> data = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    /**
     * 插入redis
     * @param count
     */
    public void insertUserRedis(long count) {
        for (long i = 0; i < count; i++) {
            redisTemplate.opsForValue().set("user" + i, EntityFactory.getUsers(1), 60, TimeUnit.SECONDS);
        }
    }

    /**
     * 插入mysql - jpa
     * @param count
     */
    public void insertUserJpa(long count) {
        for (long i = 0; i < count; i++) {
            userRepository.save(EntityFactory.getUser());
        }
    }

    /**
     * 插入mysql - mybatis
     * @param count
     */
    public void insertUserMybatis(long count) {
        for (long i = 0; i < count; i++) {
            userMapper.insert(EntityFactory.getUser());
        }
    }


    /**
     * 
     * @param count
     */
    public void newUser(long count) {
        for (long i = 0; i < count; i++) {
            EntityFactory.getUser();
        }
    }


}
