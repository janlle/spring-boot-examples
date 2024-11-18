package com.leone.boot.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class}
 * @author leone
 * @since 2018-05-11
 **/
@EnableCaching
@SpringBootApplication(
  //exclude = {DataSourceAutoConfiguration.class
  //DataSourceTransactionManagerAutoConfiguration.class,
  //HibernateJpaAutoConfiguration.class
  // }
)
@MapperScan("com.leone.boot.data.mybatis.mapper")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
