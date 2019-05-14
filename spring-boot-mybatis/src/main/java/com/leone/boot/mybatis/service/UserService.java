package com.leone.boot.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leone.boot.common.entity.User;
import com.leone.boot.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author leone
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
     * @param user 用户实体
     * @return 改变的条数
     */
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /**
     * 批量插入
     *
     * @param user 用户实体
     * @return 改变的条数
     */
    public int insertBatch(List<User> user) {
        return userMapper.insertBatch(user);
    }


    /**
     * 更新
     *
     * @param user 用户实体
     * @return 改变的条数
     */
    public int update(User user) {
        return userMapper.update(user);
    }

    /**
     * 批量更新
     *
     * @param users 用户实体
     * @return 改变的条数
     */
    public int updateBatch(List<User> users) {
        return userMapper.updateBatch(users);
    }


    /**
     * 分页
     *
     * @param page 起始页码
     * @param size 每页大小
     * @return 分页对象
     */
    public PageInfo<User> page(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.findAll();
        return new PageInfo<>(userList);
    }


    /**
     * 根据主键删除
     *
     * @param userId 用户id
     */
    public int delete(Long userId) {
        return userMapper.deleteByUserId(userId);
    }

    /**
     * 批量删除
     *
     * @param userIds 用户id列表
     */
    public int deleteByIds(List<Long> userIds) {
        return userMapper.deleteByUserIds(userIds);
    }


    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return 用户实体
     */
    public User findOne(Long userId) {
        return userMapper.findByUserId(userId);
    }

    /**
     * 查询所有
     *
     * @return 用户list
     */
    public List<User> list() {
        return userMapper.findAll();
    }
}
