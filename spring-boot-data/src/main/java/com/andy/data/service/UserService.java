package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.repository.jpa.repository.UserRepository;
import com.andy.data.serializ.mybatis.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leone
 * @since 2018-07-08
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RedisTemplate redisTemplate;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(long userId) {
        User user = userRepository.getOne(userId);
        return user;
    }

}
