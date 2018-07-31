package com.andy.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: lyon
 * @since: 2018-05-19 20:46
 **/
@RestController
@EnableRedisHttpSession
@SpringBootApplication
public class SessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class, args);
    }

    @GetMapping("/hello")
    public Object hello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "james");
        return request.getSession().getAttribute("name");
    }
}
