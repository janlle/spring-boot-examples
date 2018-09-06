package com.andy.data.controller;

import com.andy.data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * @author Leone
 * @since 2018-06-28
 **/
@RestController
@RequestMapping("/concurrent")
public class ConcurrentController {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentController.class);


    @Autowired
    private UserService userService;

    private int a = 0;

    private static Map<Integer, Boolean> data = new HashMap<>();

    private Stack stack = new Stack();

    {
        for (int i = 0; i < 10000; i++) {
            data.put(i, true);
            stack.push(i);
        }
        logger.info("初始化map...");
    }

    @RequestMapping("/test")
    public void test() {
//        log.info("test method...a={}", stack.pop());
        Random random = new Random();
        logger.info("user");
//        logger.info("user: {}", userService.getUser(random.nextInt(20) + 1));
    }

}
