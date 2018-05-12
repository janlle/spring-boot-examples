package com.andy.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-22 15:17
 **/
@Slf4j
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        log.info("test method...");
        return "test";
    }




}
