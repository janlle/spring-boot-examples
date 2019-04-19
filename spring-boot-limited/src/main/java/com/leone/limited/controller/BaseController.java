package com.leone.limited.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class BaseController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/choose")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        log.info("port: {} address: {}", port, request.getRemoteAddr());
        return String.format("port: %s address: %s",port,request.getRemoteAddr());
    }

}
