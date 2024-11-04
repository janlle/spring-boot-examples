package com.leone.boot.starter.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 *
 * #@ConditionalOnBean：仅仅在当前上下文中存在某个对象时，才会实例化一个Bean。
 * #@ConditionalOnClass：某个class位于类路径上，才会实例化一个Bean。
 * #@ConditionalOnExpression：当表达式为true的时候，才会实例化一个Bean。
 * #@ConditionalOnMissingBean：仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean。
 * #@ConditionalOnMissingClass：某个class类路径上不存在的时候，才会实例化一个Bean。
 * #@ConditionalOnNotWebApplication：不是web应用，才会实例化一个Bean。
 * #@ConditionalOnBean：当容器中有指定Bean的条件下进行实例化。
 * #@ConditionalOnMissingBean：当容器里没有指定Bean的条件下进行实例化。
 * #@ConditionalOnClass：当classpath类路径下有指定类的条件下进行实例化。
 * #@ConditionalOnMissingClass：当类路径下没有指定类的条件下进行实例化。
 * #@ConditionalOnWebApplication：当项目是一个Web项目时进行实例化。
 * #@ConditionalOnNotWebApplication：当项目不是一个Web项目时进行实例化。
 * #@ConditionalOnProperty：当指定的属性有指定的值时进行实例化。
 * #@ConditionalOnExpression：基于SpEL表达式的条件判断。
 * #@ConditionalOnJava：当JVM版本为指定的版本范围时触发实例化。
 * #@ConditionalOnResource：当类路径下有指定的资源时触发实例化。
 * #@ConditionalOnJndi：在JNDI存在的条件下触发实例化。
 * #@ConditionalOnSingleCandidate：当指定的Bean在容器中只有一个，或者有多个但是指定了首选的Bean时触发实例化。
 *
 * @author leone
 * @since 2024-11-01
 **/
@AutoConfiguration
// @ConditionalOnClass(CustomService.class)
// @ConditionalOnProperty(prefix = "customer", value = "enabled", matchIfMissing = true)
@Import({CustomProperties.class, CustomService.class})
public class CustomAutoConfiguration {

    @Autowired
    private CustomProperties customProperties;

    @Bean
    public CustomService customService() {
        CustomService customService = new CustomService();
        customService.setCustomProperties(customProperties);
        System.out.println("配置成功");
        return customService;
    }


} 