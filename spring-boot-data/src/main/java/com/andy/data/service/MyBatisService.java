package com.andy.data.service;

import com.andy.data.util.EntityFactory;
import com.andy.data.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MyBatisService {

    @Autowired
    private UserMapper userMapper;

    public long insertList(Integer count) {
        long start = System.currentTimeMillis();
        userMapper.insertList(EntityFactory.getUsers(count));
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public long insertForeach(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userMapper.insert(EntityFactory.getUsers(1).get(0));
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

}
