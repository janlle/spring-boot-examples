package com.leone.boot.data.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.utils.EntityFactory;
import com.leone.boot.data.repository.mybatis.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *
 * @author leone
 * @since 2018-05-24
 **/
@Slf4j
@Service
//@Transactional
public class MybatisService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 批量插入
     *
     * @param count
     * @return
     */
    public long insertBatch(Integer count) {
        if (count < 1000) {
            return userMapper.insertBatch(EntityFactory.getUsers(count));
        } else {
            int time = 0;
            EntityFactory.getUsers(100);
            int a = count % 1000;
            int b = count / 1000;
            for (int i = 0; i < b; i++) {
                time += userMapper.insertBatch(EntityFactory.getUsers(1000));
            }

            if (a == 0) {
                return time;
            }
            return time + userMapper.insertBatch(EntityFactory.getUsers(a));
        }
    }

    /**
     * 循环插入
     *
     * @param count
     * @return
     */
    public long insertForeach(Integer count) {
        for (long i = 0; i < count; i++) {
            userMapper.insert(EntityFactory.getUser());
        }
        return count;
    }

    @Transactional
    public long update(User user) {
        Integer result = userMapper.updateById(user);
        int i = 100 / 0;
        return 0;
    }

}
