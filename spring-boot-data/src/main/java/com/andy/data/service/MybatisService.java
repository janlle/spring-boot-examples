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
        userMapper.insertBatch(EntityFactory.getUsers(count));
        return 0;
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
        return 0;
    }

    @Transactional
    public long update(User user) {
        Integer result = userMapper.updateById(user);
        int i = 100 / 0;
        return 0;
    }


}
