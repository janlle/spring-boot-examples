package com.leone.mybatis.service;

import com.leone.common.entity.User;
import com.leone.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 插入
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


    /**
     * 更新
     *
     * @param user
     * @return
     */
    public int update(User user) {
        return userMapper.update(user);
    }

    /**
     * 批量更新
     *
     * @param users
     * @return
     */
    public int updateBatch(List<User> users) {
        return userMapper.updateBatch(users);
    }


    /**
     * 分页
     *
     * @param start
     * @param size
     * @return
     */
    public List<User> page(Integer start, Integer size) {
        return userMapper.page(start, size);
    }


    /**
     * 根据主键删除
     *
     * @param userId
     */
    public int delete(Long userId) {
        return userMapper.deleteByUserId(userId);
    }

    /**
     * 批量删除
     *
     * @param userIds
     */
    public int deleteByIds(List<Long> userIds) {
        return userMapper.deleteByUserIds(userIds);
    }

    public User selectById(Long userId) {
        return userMapper.findByUserId(userId);
    }
}
