package com.leone.boot.flux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * webflux redis configuration
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
    }

    @Bean
    public ReactiveRedisConnection connection(ReactiveRedisConnectionFactory connectionFactory) {
        return connectionFactory.getReactiveConnection();
    }

    public void flushDb() {
        factory.getConnection().flushDb();
    }
}