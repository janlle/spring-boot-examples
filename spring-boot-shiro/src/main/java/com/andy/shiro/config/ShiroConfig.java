package com.andy.shiro.config;


import com.andy.shiro.filter.CoreFilter;
import com.andy.shiro.filter.TokenFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: Leone
 * @since: 2018-04-21
 * @see org.apache.shiro.web.filter.mgt.DefaultFilter
 **/
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroProperty shiroProperty/*, TokenFilter tokenFilter, CoreFilter coreFilter*/) {
        logger.info("init shiro filter");
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
        /*swaggerFilterChain(filterChainDefinitionMapping);
        this.setUrl(filterChainDefinitionMapping, "anon", shiroProperty.getAnonUrls());
        this.setUrl(filterChainDefinitionMapping, "core,anon", shiroProperty.getCoreUrls());
        this.setUrl(filterChainDefinitionMapping, "core,auth", shiroProperty.getAuthUrls());*/

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager);
//        Map<String, Filter> filters = new HashMap();
//        filters.put("core", new CoreFilter());
//        filters.put("auth", new TokenFilter());

//        filters.put("core", tokenFilter);
//        filters.put("auth", coreFilter);
//        shiroFilter.setFilters(filters);

//        shiroFilter.setLoginUrl("/api/login");

        logger.info("shiro filter init success");
        return shiroFilter;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    //将自己的验证方式加入容器
    @Bean
    public AuthRealm authRealm(/*CredentialMatcher credentialMatcher*/) {
        AuthRealm authRealm = new AuthRealm();
//        authRealm.setCredentialsMatcher(credentialMatcher);
        return authRealm;
    }



    /*private void setUrl(Map<String, String> filterChainDefinitionMapping, String filterName, List<String> urls) {
        if (urls != null && urls.size() > 0) {
            Iterator var4 = urls.iterator();
            while (var4.hasNext()) {
                String url = (String) var4.next();
                if (!StringUtils.isEmpty(url)) {
                    filterChainDefinitionMapping.put(url, filterName);
                }
            }

        }
    }

    public void swaggerFilterChain(Map filterMapping) {
        filterMapping.put("/v2/api-docs", "anon");
        filterMapping.put("/configuration/**", "anon");
        filterMapping.put("/webjars/**", "anon");
        filterMapping.put("/swagger**", "anon");
    }*/

/*    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }*/



    /**
     * 密码匹配器
     *
     * @return
     */
    /*@Bean
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }*/

    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }*/

    // UnavailableSecurityManagerException
//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }


}
