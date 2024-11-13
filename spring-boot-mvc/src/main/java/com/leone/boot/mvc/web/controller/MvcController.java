package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.shiro.service.ShiroTokenService;
import com.leone.boot.mvc.shiro.service.UserHelper;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
@RestController
@RequestMapping("/api")
public class MvcController {

    private final String TARGET_HOST = "http://localhost:8080";

    private RestTemplate restTemplate;

    @Autowired
    private ShiroTokenService shiroTokenService;

    @GetMapping("/login")
    public String login(String userId, String role) {
        return shiroTokenService.login(userId, role);
    }

    @GetMapping("/logout")
    public String logout(String userId) {
        return shiroTokenService.logout(userId);
    }

    @GetMapping("/test")
    public String test() {
        Subject subject = UserHelper.subject;
        return subject.getPrincipal().toString();
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/form")
    public String form() {
        return "simple_form";
    }

}