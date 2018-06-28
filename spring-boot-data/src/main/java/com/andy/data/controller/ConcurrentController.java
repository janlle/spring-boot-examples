package com.andy.data.controller;

import com.andy.data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-28 22:49
 **/
@Slf4j
@RestController
public class ConcurrentController {


    @Autowired
    private UserService userService;

    private static Map<Integer, Boolean> data = new HashMap<>();

    private Stack stack = new Stack();

    {
        for (int i = 0; i < 10000; i++) {
            data.put(i, true);
            stack.push(i);
        }
        log.info("初始化map...");
    }

    private int a = 0;

    @ResponseBody
    @RequestMapping("/test")
    public void test(){
//        log.info("test method...a={}", stack.pop());
        Random random = new Random();
        log.info("user: {}", userService.findOne(random.nextInt(20)+1));
    }

}
