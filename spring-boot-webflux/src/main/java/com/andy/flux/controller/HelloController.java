package com.andy.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}


//	@GetMapping("/hello/{latency}")
//	public String helloLatency(@PathVariable long latency) {
//
//		try {
//			TimeUnit.MILLISECONDS.sleep(latency);
//		} catch (InterruptedException e) {
//			return "Error during thread sleep";
//		}
//		return "Welcome to reactive world ~";
//	}
