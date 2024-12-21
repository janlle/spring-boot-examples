package com.leone.boot.empty.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * <p>
 * filterChain
 * userDetailsService
 * webSecurityCustomizer
 * authenticationManager
 *
 * @author leone
 * @since 2024-12-18
 **/
@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * AbstractHttpConfigurer::disable
     * .formLogin(Customizer.withDefaults())
     * .csrf(AbstractHttpConfigurer::disable)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("filterChain");
        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/", "/js/**", "/css/**")
            .permitAll()
            .anyRequest()
            .authenticated()
          )
          .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * 可以使用 .requestMatchers("/", "/js/**", "/css/**").permitAll() 替代
     */
    //@Bean
    //public WebSecurityCustomizer webSecurityCustomizer() {
    //    return new WebSecurityCustomizer() {
    //        @Override
    //        public void customize(WebSecurity web) {
    //            web.ignoring().requestMatchers("/js/**", "/css/**", "/html/**");
    //        }
    //    };
    //}

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
          .password("admin")
          .roles("admin")
          .build();

        UserDetails user = User.withUsername("user")
          .password("user")
          .roles("user")
          .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


}
