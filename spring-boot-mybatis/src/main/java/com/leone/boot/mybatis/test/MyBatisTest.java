package com.leone.boot.mybatis.test;

import com.leone.boot.common.entity.User;
import com.leone.boot.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-04
 **/
public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatisConfig.xml"));

        // 获取session对象
        SqlSession session = sqlSessionFactory.openSession();
        // 创建实体对象
        User user = new User();
        user.setDescription("hello world");
        user.setAccount("toby");
        user.setPassword("123");
        user.setAge(23);

        UserMapper mapper = session.getMapper(UserMapper.class);
        User u = mapper.findByUserId(1470805L);

        // 保存数据到数据库中
        // session.insert("com.leone.boot.mybatis.mapper.UserMapper.insert", user);

        // 提交事务,这个是必须要的,否则即使sql发了也保存不到数据库中
        session.commit();

        // 关闭资源
        session.close();
    }

}
