package com.leone.boot.shiro.config;

import com.leone.boot.shiro.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-18
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private TokenInterceptor tokenInterceptor;

    @Autowired
    public WebConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("index");
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/logout").setViewName("logout");
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOriginPatterns("*")
          .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
          .allowCredentials(true)
          .maxAge(3600);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
