package com.leone.boot.data.mybatis.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * <p> 多数据源，读写分离
 *
 * @author leone
 * @since 2021-01-21
 **/
@Configuration
public class DataSourceConfig {

    //@Bean("datasource")
    //@ConfigurationProperties(prefix = "spring.datasource")
    //public DataSource datasource(DataSourceProperties properties) {
    //    return DataSourceBuilder.create(properties.getClassLoader())
    //      .type(HikariDataSource.class)
    //      .driverClassName(properties.determineDriverClassName())
    //      .url(properties.determineUrl())
    //      .username(properties.determineUsername())
    //      .password(properties.determinePassword())
    //      .build();
    //}

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        //DataSourceProperties properties
        //return DataSourceBuilder.create(properties.getClassLoader())
        //  .type(HikariDataSource.class)
        //  .driverClassName(properties.determineDriverClassName())
        //  .url(properties.determineUrl())
        //  .username(properties.determineUsername())
        //  .password(properties.determinePassword())
        //  .build();
        return DataSourceBuilder.create().build();
    }

    @Bean("slaver")
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource slaver() {
        //DataSourceProperties properties
        //return DataSourceBuilder.create(properties.getClassLoader())
        //  .type(HikariDataSource.class)
        //  .driverClassName(properties.determineDriverClassName())
        //  .url(properties.determineUrl())
        //  .username(properties.determineUsername())
        //  .password(properties.determinePassword())
        //  .build();
        return DataSourceBuilder.create().build();
    }

    /**
     * 实例化数据源路由
     */
    @Bean
    public DataSourceRouter dynamicDB(@Qualifier("master") DataSource masterDataSource,
                                      @Autowired(required = false) @Qualifier("slaver") DataSource slaveDataSource) {
        DataSourceRouter dynamicDataSource = new DataSourceRouter();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
        if (slaveDataSource != null) {
            targetDataSources.put(DataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    /**
     * 配置sessionFactory
     *
     * @param dynamicDataSource datasource
     * @return SqlSessionFactory
     * @throws Exception ex
     */
    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }

    /**
     * 创建sqlTemplate
     *
     * @param sqlSessionFactory sqlSession
     * @return SqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 事务配置
     *
     * @param dynamicDataSource datasource
     * @return transactionManager
     */
    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDB") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }

}