package com.andy.mvc.web.controller;

import com.andy.mvc.service.UserService;
import com.andy.starter.config.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private final String TARGET_HOST = "http://localhost:8080";

    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;


    @Autowired
    private HelloService helloService;
	

//    public HelloController() {
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setDefaultMaxPerRoute(1000);
//        connectionManager.setMaxTotal(1000);
//        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(
//                HttpClientBuilder.create().setConnectionManager(connectionManager).build()
//        ));
//    }

    @GetMapping("/hello/{latency}")
    public String hello(@PathVariable int latency) {
        return restTemplate.getForObject(TARGET_HOST + "/hello/" + latency, String.class);
    }

    @GetMapping("insertMybatis/{num}")
    public String insert(@PathVariable("num")Integer num) {
        long start = System.currentTimeMillis();
        userService.insertUserMybaits(num);
        long end = System.currentTimeMillis();
        double time = (double)(end-start)/1000;
        return "使用mybaits插入"+num+"条数据到mysql一共用了"+time+"秒！";
    }


    @GetMapping("insertJpa/{num}")
    public String insertJpa(@PathVariable("num")Integer num) {
        long start = System.currentTimeMillis();
        userService.insertUserJpa(num);
        long end = System.currentTimeMillis();
        double time = (double)(end-start)/1000;
        return "使用jpa插入"+num+"条数据到mysql一共用了"+time+"秒！";
    }

    @GetMapping("/customerStarter")
    public String customerStarter() {
        return helloService.sayHello();
    }


    @GetMapping("/new/{num}")
    public String newUser(@PathVariable("num")Integer num) {
        userService.newUser(num);
        return "jvm测试，新建了"+num+"个user对象！";
    }

    @GetMapping("/mvc/hello")
    public String hello() {
        return "hello world";
    }
	
}