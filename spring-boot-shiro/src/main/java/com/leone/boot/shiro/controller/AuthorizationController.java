package com.leone.boot.shiro.controller;

import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.util.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p> shiro 配置需要两个 /login 接口,一个是get用来获取登陆页面,一个用post用于登录的
 *
 * @author leone
 * @since 2018-05-18
 **/
@Slf4j
@Controller
public class AuthorizationController {

    /**
     * 查询用户
     */
    @RequestMapping("/user/list")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public String listList() {
        // Subject subject = SecurityUtils.getSubject();
        // boolean admin = subject.hasRole("admin");
        // System.out.println(admin);
        // return "user: " + admin;
        return "user-list";
    }

    /**
     * 符合user:view和user:add权限要求
     * 添加用户
     */
    @GetMapping("/user/add")
    @RequiresPermissions(value = {"user:select", "user:add"}, logical = Logical.AND)
    public String userAdd() {
        return "user-add";
    }

    @PutMapping("/user/update")
    @RequiresPermissions("user:update")
    public String userUpdate(@RequestBody SysUser user) {
        return "userUpdate";
    }


    @RequiresGuest
    @GetMapping("/guest")
    public String guest() {
        return "guest";
    }

    @RequiresUser
    @GetMapping("/user")
    public String requiresUser() {
        String user = UserHelper.userId();
        log.info(user);
        return "user";
    }


}