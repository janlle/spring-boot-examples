package com.andy.shiro.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-08
 **/
@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager() {
        logger.info("--------------ehCacheManager init---------------");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:cache/ehcache-shiro.xml");
        logger.info("--------------ehCacheManager init---------------"+cacheManager);
        return cacheManager;
    }



    @Bean(name = "redisCacheManager")
    @Primary
    public RedisCacheManager redisCacheManager() {
        logger.info("--------------redis cache init---------------");
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisTemplate(redisTemplate);
        logger.info("--------------redis cache ---------------"+cacheManager);
        return cacheManager;
    }

    /**
     * 配置shiro redisManager
     * 网上的一个 shiro-redis 插件，实现了shiro的cache接口、CacheManager接口就
     *
     * @return
     */
//    @Bean
//    public RedisManager redisManager(RedisProperties redisProperties) {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(redisProperties.getHost());
//        redisManager.setPort(redisProperties.getPort());
//        redisManager.setDatabase(redisProperties.getDatabase());
//        //redisManager.setExpire();// 配置过期时间
//        redisManager.setTimeout(redisProperties.getTimeout());
//        redisManager.setPassword(redisProperties.getPassword());
//        return redisManager;
//    }

}
