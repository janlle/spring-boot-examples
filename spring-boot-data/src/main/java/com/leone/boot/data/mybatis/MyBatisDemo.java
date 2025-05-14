package com.leone.boot.data.mybatis;

import com.leone.boot.common.entity.User;
import com.leone.boot.data.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
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
public class MyBatisDemo {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("classpath:mybatis/mybatis-config.xml"));
        // 获取session对象
        SqlSession session = sqlSessionFactory.openSession();
        // 创建实体对象
        User user = new User();
        user.setDescription("hello world");
        user.setAccount("james");
        user.setPassword("123456");
        user.setAge(18);

        // 保存数据到数据库中
        session.insert("com.leone.boot.data.mybatis.mapper.UserMapper.insert", user);

        // 查询
        UserMapper mapper = session.getMapper(UserMapper.class);
        User u = mapper.findByUserId(1470805L);

        // 提交事务,这个是必须要的,否则即使sql发了也保存不到数据库中
        session.commit();

        // 关闭资源
        session.close();
    }

}
