package com.leone.boot.mvc.shiro;

import com.leone.boot.mvc.shiro.filter.TokenFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leone
 **/
@Configuration
public class ShiroConfig {

    private final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * shiro 核心过滤器
     *
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(ShiroProperties shiroProperties) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
        swaggerFilterChain(filterChainDefinitionMapping);
        setUrl(filterChainDefinitionMapping, "anon", shiroProperties.getAnonUrls());
        setUrl(filterChainDefinitionMapping, "auth", shiroProperties.getAuthUrls());
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager());

        Map<String, Filter> filters = new HashMap<>(1);
        filters.put("auth", new TokenFilter());
        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    /**
     * 设置url
     *
     * @param mapping
     * @param filterName
     * @param urls
     */
    private void setUrl(Map<String, String> mapping, String filterName, Collection<String> urls) {
        if (!urls.isEmpty()) {
            for (String url : urls) {
                logger.info("Mapped \"{[{}]}\"", url);
                if (!StringUtils.isEmpty(url)) {
                    mapping.put(url, filterName);
                }
            }
        }
    }

    /**
     * 设置swagger过滤的url
     *
     * @param filterChainDefinitionMapping
     */
    private static void swaggerFilterChain(Map<String, String> filterChainDefinitionMapping) {
        filterChainDefinitionMapping.put("/v2/api-docs", "anon");
        filterChainDefinitionMapping.put("/configuration/**", "anon");
        filterChainDefinitionMapping.put("/webjars/**", "anon");
        filterChainDefinitionMapping.put("/swagger**", "anon");
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        // 禁用sessionStorage
        DefaultSubjectDAO de = (DefaultSubjectDAO) manager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) de.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

        // 设置token realm
        manager.setRealm(tokenRealm());

        // 无状态主题工程，禁止创建session
        manager.setSubjectFactory(subjectFactory());

        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    @Bean
    public TokenRealm tokenRealm() {
        return new TokenRealm();
    }

    @Bean
    public StatelessDefaultSubjectFactory subjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizationAttributeSourceAdvisor advisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
