package com.leone.data.service;

import com.leone.data.entity.User;
import com.leone.data.repository.mybatis.UserMapper;
import com.leone.data.utils.EntityFactory;
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
