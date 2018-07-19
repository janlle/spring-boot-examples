package com.andy.pay.shiro.config;


import com.andy.pay.shiro.AuthRealm;
import com.andy.pay.shiro.StatelessDefaultSubjectFactory;
import com.andy.pay.shiro.filter.CoreFilter;
import com.andy.pay.shiro.filter.TokenFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroProperty shiroProperty, CoreFilter coreFilter, TokenFilter tokenFilter) {
//
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
//
//        Map<String, Filter> filters = new HashMap();
//        filters.put("core", coreFilter);
//        filters.put("auth", tokenFilter);
//
//        shiroFilter.setFilters(filters);
//
////        this.setUrl(filterChainDefinitionMapping, "core,anon", shiroProperty.getCoreUrls());
////        this.setUrl(filterChainDefinitionMapping, "core,auth", shiroProperty.getAuthUrls());
////        this.setUrl(filterChainDefinitionMapping, "anon", shiroProperty.getAnonUrls());
//        this.setUrl(filterChainDefinitionMapping, "anon", Arrays.asList("/"));
//        this.setUrl(filterChainDefinitionMapping, "core,anon", Arrays.asList("/api/user/property"));
//        this.setUrl(filterChainDefinitionMapping, "core,auth", Arrays.asList("/**"));
//
//        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
//        swaggerFilterChain(filterChainDefinitionMapping);
//        logger.info("shiro filter init success");
//        return shiroFilter;
//    }
//
//    private void setUrl(Map<String, String> filterChainDefinitionMapping, String filterName, List<String> urls) {
//        if (urls != null && urls.size() > 0) {
//            Iterator var4 = urls.iterator();
//            while (var4.hasNext()) {
//                String url = (String) var4.next();
//                if (!StringUtils.isEmpty(url)) {
//                    filterChainDefinitionMapping.put(url, filterName);
//                }
//            }
//
//        }
//    }
//
//    public void swaggerFilterChain(Map filterMapping) {
//        logger.info("swagger");
//        filterMapping.put("/v2/api-docs", "anon");
//        filterMapping.put("/configuration/**", "anon");
//        filterMapping.put("/webjars/**", "anon");
//        filterMapping.put("/swagger**", "anon");
//        filterMapping.put("/swagger-ui.html", "anon");
//    }
//
//    @Bean(name = {"securityManager"})
//    public SecurityManager securityManager(AuthRealm authRealm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//
//        DefaultSubjectDAO de = (DefaultSubjectDAO) manager.getSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) de.getSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
////        StatelessDefaultSubjectFactory statelessDefaultSubjectFactory = new StatelessDefaultSubjectFactory();
////
////        manager.setSubjectFactory(statelessDefaultSubjectFactory);
//        manager.setSessionManager(this.defaultSessionManager());
//        manager.setRealm(authRealm);
//        SecurityUtils.setSecurityManager(manager);
//
//
//        return manager;
//    }
//
//    @Bean
//    public DefaultSessionManager defaultSessionManager() {
//        DefaultSessionManager manager = new DefaultSessionManager();
//        manager.setSessionValidationSchedulerEnabled(false);
//        return manager;
//    }
//
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthRealm authRealm() {
//        AuthRealm authRealm = new AuthRealm();
//        return authRealm;
//    }
//
//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }

    //Filter工厂，设置对应的过滤条件和跳转条件
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, CoreFilter coreFilter, TokenFilter tokenFilter) {
//        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        bean.setSecurityManager(securityManager);
//
//        Map<String, Filter> filters = new HashMap();
//        filters.put("core", coreFilter);
//        filters.put("auth", tokenFilter);
//
//        bean.setFilters(filters);
//
//        LinkedHashMap<String, String> filterChain = new LinkedHashMap<>();
//        filterChain.put("/index", "authc");
//        filterChain.put("/static/**", "anon");
//        filterChain.put("/login", "anon");
////        filterChain.put("/api/user/property", "core,auth");
//        filterChain.put("/api/user/property", "auth");
//        filterChain.put("/**", "user");
//        bean.setFilterChainDefinitionMap(filterChain);
//        return bean;
//    }

//    @Bean
//    public SecurityManager securityManager(AuthRealm authRealm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(authRealm);
//        return manager;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }

//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//
//    // UnavailableSecurityManagerException
//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }



    public ShiroConfig() {
        logger.info("shiro config init");
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroProperty shiroProperty) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
        swaggerFilterChain(filterChainDefinitionMapping);
        this.setUrl(filterChainDefinitionMapping, "anon", shiroProperty.getAnonUrls());
        this.setUrl(filterChainDefinitionMapping, "cors,anon", shiroProperty.getCoreUrls());
        this.setUrl(filterChainDefinitionMapping, "cors,auth", shiroProperty.getAuthUrls());
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap();
        filters.put("cors", new CoreFilter());
        filters.put("auth", new TokenFilter());
        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    private void setUrl(Map<String, String> filterChainDefinitionMapping, String filterName, List<String> urls) {
        if (urls != null) {
            Iterator var4 = urls.iterator();

            while (var4.hasNext()) {
                String url = (String) var4.next();
                if (!StringUtils.isEmpty(url)) {
                    filterChainDefinitionMapping.put(url, filterName);
                }
            }

        }
    }

    public static void swaggerFilterChain(Map<String, String> filterChainDefinitionMapping) {
        filterChainDefinitionMapping.put("/v2/api-docs", "anon");
        filterChainDefinitionMapping.put("/configuration/**", "anon");
        filterChainDefinitionMapping.put("/webjars/**", "anon");
        filterChainDefinitionMapping.put("/swagger**", "anon");
    }

    @Bean
    public SecurityManager securityManager(AuthRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        DefaultSubjectDAO de = (DefaultSubjectDAO) manager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) de.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        manager.setRealm(realm);
        StatelessDefaultSubjectFactory statelessDefaultSubjectFactory = new StatelessDefaultSubjectFactory();
        manager.setSubjectFactory(statelessDefaultSubjectFactory);
        manager.setSessionManager(this.defaultSessionManager());
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    @Bean
    public DefaultSessionManager defaultSessionManager() {
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
