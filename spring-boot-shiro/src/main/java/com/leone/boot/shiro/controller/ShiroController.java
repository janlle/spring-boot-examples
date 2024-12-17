package com.leone.boot.shiro.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.leone.boot.shiro.util.UserHelper;
import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


/**
 * <p> shiro 配置需要两个 /login 接口,一个是get用来获取登陆页面,一个用post用于登录的
 *
 * @author leone
 * @since 2018-05-18
 **/
@Controller
@RequestMapping("/shiro")
public class ShiroController {

    private static final Logger log = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String account,
                        @RequestParam String password,
                        HttpSession session,
                        @RequestHeader("Host") String host) {
        SysUser loginUser = userService.findUserByAccount(account);
        if (!ObjectUtils.isEmpty(loginUser)) {
//            if (new Md5Hash(password, loginUser.getSalt()).toString().equals(loginUser.getPassword())) {
            if (DigestUtil.md5Hex(password).equals(loginUser.getPassword())) {
                session.setAttribute("user", loginUser);
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(account, DigestUtil.md5Hex(password), true, host));
                if (subject.isAuthenticated()) {
                    return "index";
                }
            }
        }
        return "403";
    }

    @RequestMapping("/image")
    public void validCode(HttpServletResponse response, HttpSession session) throws Exception {
        //String code = ImageCodeUtil.generate(response, 60, 18);
        log.info("code: {}", "code");
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "index");
        return "index";
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
    public String update(@RequestBody SysUser user) {
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