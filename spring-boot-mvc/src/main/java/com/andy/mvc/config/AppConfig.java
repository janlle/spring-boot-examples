package com.andy.mvc.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-03 18:58
 **/
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(connectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }


//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        final RedisTicketRegistryProperties redis = casProperties.getTicket().getRegistry().getRedis();
//        final JedisPoolConfig poolConfig = redis.getPool() != null ? jedisPoolConfig() : new JedisPoolConfig();
//
//        final JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
//        factory.setHostName(redis.getHost());
//        factory.setPort(redis.getPort());
//        if (redis.getPassword() != null) {
//            factory.setPassword(redis.getPassword());
//        }
//        factory.setDatabase(redis.getDatabase());
//        if (redis.getTimeout() > 0) {
//            factory.setTimeout(redis.getTimeout());
//        }
//        return factory;
//    }




}
