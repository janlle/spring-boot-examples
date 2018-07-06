package com.andy.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-06 21:17
 **/
@RestController
public class webController {

    @GetMapping("/socket")
    public String socket(HttpServletRequest request, HttpServletResponse response) {



        return "success";
    }

}
