package com.andy.concurrency.controller;

import com.andy.concurrency.example.commonUnsafe.HashMapExample;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-22 15:17
 **/
@Slf4j
@RestController
public class TestController {

    private static Map<Integer, Boolean> data = new HashMap<>();

    Stack stack = new Stack();

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
        log.info("test method...a={}", stack.pop());
    }

}
