package com.leone.boot.security.config;

import com.leone.boot.security.service.IUserService;
import com.leone.boot.security.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * filterChain
 * userDetailsService
 * webSecurityCustomizer
 *
 * @author leone
 * @since 2018-04-21
 **/
@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    //@Autowired
    private IUserService databaseUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 定义安全策略
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("filterChain");
        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/")
            .permitAll()
            .anyRequest()
            .authenticated()
          )
          .csrf(AbstractHttpConfigurer::disable)    // 关闭跨站请求伪造
          .cors(AbstractHttpConfigurer::disable)    // 跨源资源共享（跨域）
          .formLogin(Customizer.withDefaults())
          .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService memoryUserDetailsService() {
        UserDetails user = User.builder()
          .username("leone")
          .password(passwordEncoder().encode("leone"))
          .roles("USER")
          .build();
        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().requestMatchers("/js/**", "/css/**", "/static/**");
            }
        };
    }

    // 旧的安全配置
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //             .antMatchers("/authentication/form", "/login")
    //             .permitAll()
    //             .anyRequest()
    //             .authenticated()
    //             .and()
    //             .logout()
    //             .permitAll()
    //             .and()
    //             .authorizeRequests()
    //             .and()
    //             .formLogin()
    //             .loginPage("/login")
    //             .and()
    //             .csrf()
    //             .disable();
    // }

    // 有以下几种形式，使用第3种
    //@Bean
    //public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    //    // 1.inMemoryAuthentication 从内存中获取
    //    auth.inMemoryAuthentication()
    //      .withUser("admin").password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin")).roles("ADMIN").and()
    //      .withUser("leone").password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("leone")).roles("USER").and();
    //
    //
    //    // 2.jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
    //    // usersByUsernameQuery 指定查询用户SQL
    //    // authoritiesByUsernameQuery 指定查询权限SQL
    //    // auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);
    //    // auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(passwordEncoder);
    //
    //    // 3.注入userDetailsService，需要实现userDetailsService接口
    //    //auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    //    System.err.println("build ... ");
    //    return auth.build();
    //}

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername 方法获取UserDetails
        authProvider.setUserDetailsService(memoryUserDetailsService());
        // 设置密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
