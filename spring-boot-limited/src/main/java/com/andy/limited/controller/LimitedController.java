package com.andy.limited.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
@RestController
@RequestMapping("/api")
public class LimitedController {


    @GetMapping("/limited")
    public String limited() {
        return "success";
    }

}
