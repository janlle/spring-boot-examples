package com.leone.boot.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import jakarta.annotation.Resource;
import java.util.Base64;
import java.util.Map;

/**
 * <p> shiro 配置
 *
 * @author leone
 * @see org.apache.shiro.web.filter.mgt.DefaultFilter
 * @since 2018-04-21
 **/
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    private static final Base64.Decoder decoder = Base64.getDecoder();

    @Resource
    private ShiroProperties shiroProperties;

    @Resource
    private RedisProperties redisProperties;

    /**
     * shiro 核心 filter
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, String> filterChainMapping = shiroFilter.getFilterChainDefinitionMap();

        // 不被被拦截的链接 顺序判断
        if (!ObjectUtils.isEmpty(shiroProperties.getAnonUrls())) {
            for (String anonUrl : shiroProperties.getAnonUrls()) {
                filterChainMapping.put(anonUrl, "anon");
            }
        }

        // 被被拦截的链接 顺序判断
        if (!ObjectUtils.isEmpty(shiroProperties.getAuthUrls())) {
            for (String authcUrl : shiroProperties.getAuthUrls()) {
                filterChainMapping.put(authcUrl, "authc");
            }
        }

        //退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainMapping.put("/shiro/logout", "logout");
        shiroFilter.setFilterChainDefinitionMap(filterChainMapping);
        shiroFilter.setLoginUrl(shiroProperties.getLoginUrl());

        logger.info("shiro filter init success");
        return shiroFilter;
    }

    @Bean
    public SecurityManager securityManager(MySqlAuthRealm mySqlAuthRealm, CacheManager cacheManager, SessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义 realm
        securityManager.setRealm(mySqlAuthRealm);

        // 设置自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager);

        // 设置自定义session管理 使用redis
        //securityManager.setSessionManager(sessionManager);

        // 设置自定义记住我管理器
        //securityManager.setRememberMeManager(rememberMeManager);

        // 注入安全管理器，里面包含了大部分信息，比较重要
        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    /**
     * @return
     */
    @Bean
    public MySqlAuthRealm mySqlAuthRealm() {
        return new MySqlAuthRealm();
    }

    /**
     * shiro session 管理器
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        //这里可以不设置 Shiro 有默认的 session 管理。如果缓存为 Redis 则需改用Redis的管理
        shiroSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSessionManager;
    }

    /**
     * shiro 记住我管理器
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
        byte[] cipherKey = decoder.decode(shiroProperties.getSecret());
        cookieRememberMeManager.setCipherKey(cipherKey);
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 如果httpOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击
        simpleCookie.setHttpOnly(true);
        // 记住我cookie生效时间,单位是秒
        simpleCookie.setMaxAge(600);
        return simpleCookie;
    }


    /**
     * 密码加密匹配管理器
     *
     * @return
     */
    @Bean
    public CredentialsMatcher credentialMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用那种摘要算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }


    /**
     * cacheManager 缓存 redis 实现,使用的是 shiro-redis 开源插件
     *
     * @param redisManager
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }


    /**
     * 配置 shiro redisManager 使用的是 shiro-redis 开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPort(redisProperties.getPort());
        redisManager.setDatabase(redisProperties.getDatabase());
        redisManager.setPassword(redisProperties.getPassword());
        return redisManager;
    }


    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持，解决shiro注解不起作用问题
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
