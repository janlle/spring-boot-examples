package com.leone.boot.mybatis.runner;

import com.leone.boot.mybatis.entity.User2;
import com.leone.boot.mybatis.mapper.User2Mapper;
import com.mybatisflex.core.MybatisFlexBootstrap;
import com.mybatisflex.core.query.QueryCondition;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

public class FlexHelloWorld {

    public static void main(String... args) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://hw:xx/boot");
        dataSource.setUsername("xx");
        dataSource.setPassword("xx");

        MybatisFlexBootstrap.getInstance()
          .setDataSource(dataSource)
          .addMapper(User2Mapper.class)
          .start();

        User2Mapper mapper = MybatisFlexBootstrap.getInstance()
          .getMapper(User2Mapper.class);

        long l = System.currentTimeMillis();
        List<User2> account = mapper.selectAll();
        System.out.println(System.currentTimeMillis() - l);
    }


}