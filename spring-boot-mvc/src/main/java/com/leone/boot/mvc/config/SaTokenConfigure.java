package com.leone.boot.mvc.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Sa-Token 是一个轻量级 java 权限认证框架，主要解决登录认证、权限认证、单点登录、OAuth2、微服务网关鉴权 等一系列权限相关问题。
 *
 * #@SaCheckLogin: 登录校验 —— 只有登录之后才能进入该方法。
 * #@SaCheckRole("admin"): 角色校验 —— 必须具有指定角色标识才能进入该方法。
 * #@SaCheckPermission("user:add"): 权限校验 —— 必须具有指定权限才能进入该方法。
 * #@SaCheckSafe: 二级认证校验 —— 必须二级认证之后才能进入该方法。
 * #@SaCheckBasic: HttpBasic校验 —— 只有通过 Basic 认证后才能进入该方法。
 * #@SaCheckDisable("comment")：账号服务封禁校验 —— 校验当前账号指定服务是否被封禁。
 * #@SaIgnore：忽略校验 —— 表示被修饰的方法或类无需进行注解鉴权和路由拦截器鉴权。
 *
 *
 * @author leone
 * @since 2024-11-13
 **/
//@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

}
