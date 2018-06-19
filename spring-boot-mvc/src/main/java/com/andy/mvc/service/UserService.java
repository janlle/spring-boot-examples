package com.andy.mvc.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.andy.mvc.dao.UserRepository;
import com.andy.mvc.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.andy.mvc.entity.User;


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
		log.info("开始执行插入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			redisTemplate.opsForValue().set("user"+i,new User(i*100,"张三"+i,"test"+i+"@126.com", 2300.23, new Date()),60, TimeUnit.SECONDS);
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}


	public void insertUserJpa(long userNum) {
		log.info("开始执行插入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			userRepository.save(new User(i*100,"张三"+i,"test"+i+"@126.com", 2300.23, new Date()));
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}

	public void insertUserMybaits(long userNum) {
		log.info("开始执行插入操作，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			userMapper.insert(new User(i*100,"张三"+i,"test"+i+"@126.com", 2300.23, new Date()));
		}
		log.info("结束插入操作，当前时间：{}", LocalDateTime.now());
	}


	public void newUser(long userNum) {
		log.info("开始新建对象，当前时间：{}", LocalDateTime.now());
		for (long i = 0; i < userNum; i++) {
			User user = new User(i*100,"张三"+i,"test"+i+"@126.com", 2300.23, new Date());
		}
		log.info("结束新建对象，当前时间：{},一共新建了:{}个user对象！", LocalDateTime.now(), userNum);
	}


}
