package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.mybatis.UserMapper;
import com.andy.data.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-05-24
 **/
@Slf4j
@Service
//@Transactional
public class MybatisService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 批量插入
     *
     * @param count
     * @return
     */
    public long insertBatch(Integer count) {
        long start = System.currentTimeMillis();
        userMapper.insertBatch(EntityFactory.getUsers(count));
        long end = System.currentTimeMillis();
        return (end - start);
    }

    /**
     * 循环插入
     *
     * @param count
     * @return
     */
    public long insertForeach(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userMapper.insert(EntityFactory.getUsers(1).get(0));
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

    @Transactional
    public int update(User user) {
        Integer result = userMapper.updateById(user);
        throw new RuntimeException("发生异常");
    }


}
