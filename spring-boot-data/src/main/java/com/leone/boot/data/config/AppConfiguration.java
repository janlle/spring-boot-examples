package com.leone.boot.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.*;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author leone
 * @since 2018-07-08
 **/
@Configuration
public class AppConfiguration {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public Converter<String, Date> timestampConvert() {
//        Converter<String, Date> timestampConvert = (String source) -> {
//            return new Date(Long.parseLong(source));
//        };
        Converter<String, Date> converter = new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                return new Date(Long.parseLong(source));
            }
        };
        return converter;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        KeyGenerator keyGenerator = (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
        return keyGenerator;
    }



}
