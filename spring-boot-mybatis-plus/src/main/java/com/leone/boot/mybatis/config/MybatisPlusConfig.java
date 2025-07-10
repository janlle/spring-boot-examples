package com.leone.boot.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.leone.boot.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 乐观锁
        //interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 动态表名
        //interceptor.addInnerInterceptor(new DynamicTableNameInnerInterceptor());

        // 多租户
        //interceptor.addInnerInterceptor(new TenantLineInnerInterceptor());

        // 攻击 SQL 阻断解析器、加入解析链
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // SQL 性能规范:
        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());

        // 分页拦截器，需要设置数据库类型（主要用于分页方言）
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}