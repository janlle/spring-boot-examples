package com.andy.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-22 15:17
 **/
@Slf4j
@RestController
public class TestController {

    private int a = 0;

    @ResponseBody
    @RequestMapping("/test")
    public void test(){
        log.info("test method...a={}", a);
        a = a++;
    }




}
