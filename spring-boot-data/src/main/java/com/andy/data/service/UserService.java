package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import com.andy.data.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


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

	public void insertUser(long userNum) {
		log.info("开始插入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			redisTemplate.opsForValue().set("user"+i,new User(i, "james" + i, "admin" + i, new Date(), 10000 + 0.1, new Date(), false), 60, TimeUnit.SECONDS);
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}


	public void insertUserJpa(long userNum) {
		log.info("开始插入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			userRepository.save(new User(i, "james" + i, "admin" + i, new Date(), 10000 + 0.1, new Date(), false));
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}

	public void insertUserMybaits(long userNum) {
		log.info("开始执入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			userMapper.insert(new User(i, "james" + i, "admin" + i, new Date(), 10000 + 0.1, new Date(), false));
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}

	public User findOne(long userId) {
		return userMapper.findByUserId(userId);
	}


}
