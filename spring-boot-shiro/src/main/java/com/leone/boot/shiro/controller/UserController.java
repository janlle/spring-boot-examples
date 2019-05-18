package com.leone.boot.shiro.controller;

import com.leone.boot.shiro.common.util.UserHelper;
import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/shiro/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{account}")
    public User user(@PathVariable String account) {
        return userService.findByAccount(account);
    }

    /**
     * 查询用户
     */
    @RequestMapping("/list")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public String list() {
//        Subject subject = SecurityUtils.getSubject();
//        boolean admin = subject.hasRole("admin");
//        System.out.println(admin);
//        return "user: " + admin;
        return "success";
    }

    /**
     * 符合user:view和user:add权限要求
     * 添加用户
     */
    @PostMapping
    @RequiresPermissions(value = {"user:select", "user:add"}, logical = Logical.AND)
    public String save() {
        return "userAdd";
    }


    @DeleteMapping
    @RequiresPermissions("user:delete")
    public String delete() {
        return "userDel";
    }

    @PutMapping
    @RequiresPermissions("user:update")
    public String update(@RequestBody User user) {
        return "userUpdate";
    }


    @RequiresGuest
    @GetMapping("/guest")
    public String RequiresGuest() {
        return "guest";
    }

    @RequiresUser
    @GetMapping("/user")
    public String RequiresUser() {
        Integer user = UserHelper.userId();
        System.out.println(user);
        return "user";
    }


}