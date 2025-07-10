package com.leone.boot.mybatis.runner;

import com.leone.boot.mybatis.entity.User2;
import com.leone.boot.mybatis.mapper.User2Mapper;
import com.mybatisflex.core.MybatisFlexBootstrap;
import com.zaxxer.hikari.HikariDataSource;

public class FlexHelloWorld {
    public static void main(String... args) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flex");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        MybatisFlexBootstrap.getInstance()
          .setDataSource(dataSource)
          .addMapper(User2Mapper.class)
          .start();

        User2Mapper mapper = MybatisFlexBootstrap.getInstance()
          .getMapper(User2Mapper.class);


        // id = 100
        User2 account = mapper.selectOneById(100);
    }


}