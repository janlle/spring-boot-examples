package com.andy.security.config;

import com.andy.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: lyon
 * @since: 2018-04-21 14:17
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    //定义安全策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    //配置哪些资源不需要认证
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }


    //有以下几种形式，使用第3种
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //1.inMemoryAuthentication 从内存中获取
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").password(new BCryptPasswordEncoder().encode("123123")).roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN").and()
                .withUser("andy").password("andy").roles("USER").and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("jack").password(new BCryptPasswordEncoder().encode("jack")).roles("USER");

        //2.jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
        //usersByUsernameQuery 指定查询用户SQL
        //authoritiesByUsernameQuery 指定查询权限SQL
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);
        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new AppPasswordEncoder());

        //3.注入userDetailsService，需要实现userDetailsService接
        auth.userDetailsService(userDetailService).passwordEncoder(new AppPasswordEncoder());

    }
}
