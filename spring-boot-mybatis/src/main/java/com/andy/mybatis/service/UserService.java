package com.andy.mybatis.service;

import com.andy.mybatis.entity.User;
import com.andy.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-04
 **/
@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void test() {
        // 初始化 影响行数
        int result = 0;
        // 初始化 User 对象
        User user = new User();

        // 插入 User (插入成功会自动回写主键到实体类)
        user.setAccount("Tom");
        result = userMapper.insert(user);

        // 更新 User
        user.setAge(18);
        result = userMapper.updateById(user);

        // 查询 User
        User exampleUser = userMapper.selectById(user.getUserId());

        // 查询姓名为‘张三’的所有用户记录
//        List<User> userList = userMapper.selectList(new EntityWrapper<User>().eq("name", "张三"));

        // 删除 User
        result = userMapper.deleteById(user.getUserId());
    }


}
