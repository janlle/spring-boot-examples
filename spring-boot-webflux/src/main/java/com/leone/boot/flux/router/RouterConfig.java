package com.leone.boot.flux.router;

import com.leone.boot.flux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author leone
 * @since 2018-05-26
 **/
@Configuration
public class RouterConfig {

    private UserHandler userHandler;

    public RouterConfig(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/time"), userHandler::getTime)
                .andRoute(RequestPredicates.GET("/helloMono"), userHandler::helloMono);
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), userHandler::helloMono)
                .andRoute(RequestPredicates.POST("/register"), userHandler::register)
                .andRoute(RequestPredicates.POST("/login"), userHandler::login)
                .andRoute(RequestPredicates.GET("/times"), userHandler::sendTimePerSec);
    }

}