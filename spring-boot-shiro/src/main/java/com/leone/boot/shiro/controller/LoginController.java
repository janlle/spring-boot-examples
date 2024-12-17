package com.leone.boot.shiro.controller;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.service.UserService;
import com.leone.boot.shiro.util.ImageCodeUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * <p> shiro 配置需要两个 /login 接口,一个是get用来获取登陆页面,一个用post用于登录的
 *
 * @author leone
 * @since 2018-05-18
 **/
@Slf4j
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String account, @RequestParam String password, HttpSession session, @RequestHeader("Host") String host) {
        password = DigestUtil.md5Hex("admin");
        SysUser login = userService.login(account, password);
        try {
            if (!ObjectUtils.isEmpty(login)) {
                session.setAttribute("user", login);
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(account, password, true, host));
                if (subject.isAuthenticated()) {
                    return "index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "403";
    }

    @RequestMapping("/image")
    public void validCode(HttpServletResponse response, HttpSession session) throws Exception {
        String code = ImageCodeUtil.generate(response, 60, 18);
        log.info("code: {}", code);
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}