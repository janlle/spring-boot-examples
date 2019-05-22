package com.leone.boot.security.config;

import com.leone.boot.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author leone
 * @since 2018-04-21
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 定义安全策略
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authentication/form","/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .csrf()
                .disable();
    }

    /**
     * 配置哪些资源不需要认证
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }


    /**
     * 有以下几种形式，使用第3种
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 1.inMemoryAuthentication 从内存中获取
        /*auth.inMemoryAuthentication()
                .withUser("admin").password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin")).roles("ADMIN").and()
                .withUser("andy").password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("andy")).roles("USER").and();*/

        // 2.jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
        // usersByUsernameQuery 指定查询用户SQL
        // authoritiesByUsernameQuery 指定查询权限SQL
        // auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);
        // auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(passwordEncoder);

        // 3.注入userDetailsService，需要实现userDetailsService接口
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);

    }
}
