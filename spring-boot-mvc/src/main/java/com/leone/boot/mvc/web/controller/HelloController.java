package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.service.UserService;
import com.leone.boot.mvc.shiro.service.ShiroTokenService;
import com.leone.boot.mvc.shiro.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private final String TARGET_HOST = "http://localhost:8080";

    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;


//    @Autowired
//    private HelloService helloService;

    @Autowired
    private ShiroTokenService shiroTokenService;

//    public HelloController() {
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setDefaultMaxPerRoute(1000);
//        connectionManager.setMaxTotal(1000);
//        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
//                HttpClientBuilder.create().setConnectionManager(connectionManager).build()
//        ));
//    }


    @GetMapping("/login")
    public String login() {
        shiroTokenService.login("12331", "2");
        return "success";
    }

    @GetMapping("/logout")
    public String logout() {
//        shiroTokenService.afterLogout(1234);
        shiroTokenService.logout("12331");
        return "logout success";
    }

    @GetMapping("/api/lyon")
    public String Leone() {
        Integer userId = UserHelper.getUserId();
        return userId.toString();
    }

}