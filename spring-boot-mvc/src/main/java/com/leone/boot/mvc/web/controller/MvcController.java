package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.shiro.service.ShiroTokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
@RestController
public class MvcController {

    private final String TARGET_HOST = "http://localhost:8080";

    private RestTemplate restTemplate;

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

    @GetMapping("/api/test")
    public String test() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(session);
        return subject.getPrincipal().toString();
    }

}