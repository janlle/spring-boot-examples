package com.andy.mybatis.service;

import com.andy.mybatis.entity.User;
import com.andy.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-03-02
 **/
@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 添加
     *
     * @param user
     * @return
     */
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /**
     * 批量插入
     *
     * @param user
     * @return
     */
    public int insertBatch(List<User> user) {
        return userMapper.insertBatch(user);
    }


    public int update(User user) {
        return userMapper.update(user);
    }

    public int updateBatch(List<User> users) {
        return userMapper.updateBatch(users);
    }


    public List<User> page(Integer start, Integer size) {
        return userMapper.page(start, size);
    }


    /**
     * 根据主键删除
     *
     * @param userId
     */
    public int delete(Long userId) {
        System.out.println(userId);
        return userMapper.deleteByUserId(userId);
    }

    /**
     * 批量删除
     *
     * @param userIds
     */
    public int deleteByIds(List<Long> userIds) {
        System.out.println(userIds);
        return userMapper.deleteByUserIds(userIds);
    }

    public User selectById(Long userId) {
        return userMapper.findByUserId(userId);
    }
}
