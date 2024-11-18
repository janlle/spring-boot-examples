package com.leone.boot.data.controller;

import com.leone.boot.data.jpa.entity.JpaUser;
import com.leone.boot.data.jpa.repository.UserRepository;
import com.leone.boot.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leone
 * @since 2018-05-11
 **/
@RestController
@RequestMapping("/api/jpa")
public class JpaController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/batchInsert")
    public String insertBatch(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = userService.jpaInsertBatch(count);
        return "batch insert " + count + " expenditure:" + time + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        long time = userService.jpaInsertForeach(count);
        return "foreach insert " + count + " expenditure:" + time + " ms!";
    }

    @PutMapping("/update")
    public String update(@RequestBody JpaUser user) {
        int result = userService.jpaUpdate(user);
        return "update one expenditure:" + result + " ms!";
    }

}
