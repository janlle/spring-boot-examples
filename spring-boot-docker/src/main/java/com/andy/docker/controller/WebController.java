package com.andy.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WebController {
	
    @GetMapping("/hello_world")
    public String sayHelloWorld() {
        return "success";
    }

}
