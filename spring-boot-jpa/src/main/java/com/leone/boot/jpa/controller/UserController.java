package com.leone.boot.jpa.controller;

import com.leone.boot.jpa.entity.User;
import com.leone.boot.jpa.pojo.UserQueryVO;
import com.leone.boot.jpa.pojo.UserVO;
import com.leone.boot.jpa.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * @author leone
 * @since 2018-05-11
 **/
@RestController
@RequestMapping("/api/jpa")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/batchInsert")
    public String insertBatch(@RequestParam(required = false, defaultValue = "1") Integer count) {
        return "batch insert " + count + " expenditure:" + "" + " ms!";
    }

    @GetMapping("/foreachInsert")
    public String insertForeach(@RequestParam(required = false, defaultValue = "1") Integer count) {
        return "foreach insert " + count + " expenditure:" + "" + " ms!";
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping
    public User findOne(@RequestParam Long userId) {
        return userService.findOne(userId);
    }

    @PostMapping
    public void delete(@RequestParam Long userId) {
        userService.delete(userId);
    }

    @PostMapping
    public Page<UserVO> page(@ModelAttribute UserQueryVO query, @PageableDefault Pageable pageable) {
        return userService.page(pageable, query);
    }

}
