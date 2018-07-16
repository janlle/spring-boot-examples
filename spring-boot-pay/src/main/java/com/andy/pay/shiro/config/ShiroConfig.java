//package com.andy.pay.shiro.config;
//
//import com.andy.pay.shiro.StatelessDefaultSubjectFactory;
//import com.andy.pay.shiro.TokenRealm;
//import com.andy.pay.shiro.filter.CoreFilter;
//import com.andy.pay.shiro.filter.TokenFilter;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.DefaultSessionManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//    @Bean(name = {"shiroFilter"})
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroProperty shiroProperty) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
//        swaggerFilterChain(filterChainDefinitionMapping);
//        this.setUrl(filterChainDefinitionMapping, "anon", shiroProperty.getAnonUrls());
//        this.setUrl(filterChainDefinitionMapping, "core,anon", shiroProperty.getCoreUrls());
//        this.setUrl(filterChainDefinitionMapping, "core,auth", shiroProperty.getAuthUrls());
//        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
//        shiroFilter.setSecurityManager(securityManager);
//        Map<String, Filter> filters = new HashMap();
//        filters.put("core", new CoreFilter());
//        filters.put("auth", new TokenFilter());
//        shiroFilter.setFilters(filters);
//        return shiroFilter;
//    }
//
//    private void setUrl(Map<String, String> filterChainDefinitionMapping, String filterName, List<String> urls) {
//        if (urls != null) {
//            Iterator var4 = urls.iterator();
//
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
//    public static void swaggerFilterChain(Map<String, String> filterChainDefinitionMapping) {
//        filterChainDefinitionMapping.put("/v2/api-docs", "anon");
//        filterChainDefinitionMapping.put("/configuration/**", "anon");
//        filterChainDefinitionMapping.put("/webjars/**", "anon");
//        filterChainDefinitionMapping.put("/swagger**", "anon");
//    }
//
//    @Bean(name = {"securityManager"})
//    public SecurityManager securityManager(TokenRealm realm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        DefaultSubjectDAO de = (DefaultSubjectDAO) manager.getSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) de.getSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        manager.setRealm(realm);
//        StatelessDefaultSubjectFactory statelessDefaultSubjectFactory = new StatelessDefaultSubjectFactory();
//        manager.setSubjectFactory(statelessDefaultSubjectFactory);
//        manager.setSessionManager(this.defaultSessionManager());
//        SecurityUtils.setSecurityManager(manager);
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
//}
