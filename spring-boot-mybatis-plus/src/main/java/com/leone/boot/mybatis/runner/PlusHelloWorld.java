package com.leone.boot.mybatis.runner;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.leone.boot.mybatis.entity.User1;
import com.leone.boot.mybatis.mapper.User1Mapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2025-07-11
 **/
public class PlusHelloWorld {

    public static void main(String... args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://hw:xx/boot");
        dataSource.setUsername("xx");
        dataSource.setPassword("xx");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //这是mybatis-plus的配置对象，对mybatis的Configuration进行增强
        MybatisConfiguration configuration = new MybatisConfiguration();
        GlobalConfig globalConfig = GlobalConfigUtils.getGlobalConfig(configuration);
        //此参数会自动生成实现baseMapper的基础方法映射
        globalConfig.setSqlInjector(new DefaultSqlInjector());
        //设置超类mapper
        globalConfig.setSuperMapperClass(BaseMapper.class);
        //设置数据源
        Environment environment = new Environment("1", new JdbcTransactionFactory(), dataSource);
        configuration.setEnvironment(environment);
        //构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(configuration);


        sqlSessionFactory.getConfiguration().addMapper(User1Mapper.class);
        long l = System.currentTimeMillis();
        // 459
        User1Mapper mapper = sqlSessionFactory.openSession().getMapper(User1Mapper.class);
        List<User1> user = mapper.selectList(null);
        //List<User1> list = Db.list(User1.class);
        System.out.println(System.currentTimeMillis() - l);

    }


}