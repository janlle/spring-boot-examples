package com.andy.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *
 * @author: lyon
 * @since: 2018-08-11
 **/
@Slf4j
@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/date")
    public String date(@RequestParam(required = false) Date date) {
        return date.toString();
    }

}
