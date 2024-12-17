package com.leone.boot.shiro.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.leone.boot.shiro.util.ImageCodeUtil;
import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


/**
 * <p> shiro 配置需要两个 /login 接口,一个是get用来获取登陆页面,一个用post用于登录的
 * 认证授权
 *
 * @author leone
 * @since 2018-05-18
 **/
@Controller
@RequestMapping("/shiro")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("password") String password,
                        @RequestHeader("Host") String host,
                        HttpSession session) {
        SysUser loginUser = userService.findUserByAccount(userId);
        if (!ObjectUtils.isEmpty(loginUser)) {
            if (DigestUtil.md5Hex(password).equals(loginUser.getPassword())) {
                session.setAttribute("user", loginUser);
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(userId, DigestUtil.md5Hex(password), true, host));
                if (subject.isAuthenticated()) {
                    return "home";
                }
            }
        }
        return "403";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        // Serializable sessionId = subject.getSession().getId();
        // log.info("sessionId: {}", sessionId);
        // JSESSIONID=7A5C6BFB8B62D51D56B2FC492CC3ECEE; Path=/; HttpOnly
        subject.logout();
        return "logout";
    }

    @RequestMapping("/image")
    public void validCode(HttpServletResponse response) throws Exception {
        String code = ImageCodeUtil.generate(response, 60, 18);
        log.info("code: {}", code);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "index");
        return "index";
    }


}