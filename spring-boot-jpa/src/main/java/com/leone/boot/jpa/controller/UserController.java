package com.leone.boot.jpa.controller;

import com.leone.boot.jpa.entity.User;
import com.leone.boot.jpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author leone
 * @since 2018-05-11
 **/
@Slf4j
@RestController
@RequestMapping("/api/jpa")
public class UserController {

    @Resource
    private UserService jpaService;


    @GetMapping("/batchInsert")
    public String insertBatch(@RequestParam(required = false, defaultValue = "1") Integer count) {
        return "batch insert " + count + " expenditure:" + "" + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        return "foreach insert " + count + " expenditure:" + "" + " ms!";
    }


    @PutMapping("/update")
    public String update(@RequestBody User user) {
        return "update one expenditure:" + "a" + " ms!";
    }

}
