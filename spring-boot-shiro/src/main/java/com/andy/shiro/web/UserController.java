package com.andy.shiro.web;

import com.andy.shiro.common.util.ImageCodeUtil;
import com.andy.shiro.config.Token;
import com.andy.shiro.entity.rbac.User;
import com.andy.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/user/one")
    public User getUser(@RequestParam String account) {
        return userService.getByAccount(account);
    }


    /**
     * 查询用户
     */

    @RequestMapping("/user/list")//符合user:view或user:add权限要求即可
    @RequiresPermissions(value = {"user:add", "user:select"}, logical = Logical.OR)
    public String user() {
        return "user";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/user/add")//符合user:view和user:add权限要求
    @RequiresPermissions(value = {"user:select", "user:add"}, logical = Logical.AND)
    public String userAdd() {
        return "userAdd";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/user/delete")
    @RequiresPermissions("user:delete")
    public String userDel() {
        return "userDel";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/user/update")
    @RequiresPermissions("user:update")
    public String userUpdate() {
        return "userUpdate";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin() {
        return "home";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        return "index";
    }
    //登录页(shiro配置需要两个/login 接口,一个是get用来获取登陆页面,一个用post用于登录,这是一个坑)
//    @GetMapping("/login")
//    public String login() {
//        log.info("跳转到login页面控制器！");
//        return "login";
//    }

    @Autowired
    private SecurityManager securityManager;

    //    @ResponseStatus(HttpStatus.GONE)
    @PostMapping(value = "/login")
    public void loginUser(@RequestParam String token, HttpSession session) {
        Token loginToken = new Token(token);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginToken);
            User user = (User) subject.getPrincipal();
            log.info("user:{}", user);
            session.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @ResponseBody
    @RequiresUser
    @RequestMapping("/guest")
    public String guest() {
        return "guest请求";
    }

    @ResponseBody
    @RequiresGuest
    @RequestMapping("/user")
    public String reqUser() {
        return "user请求";
    }

    @RequestMapping("/image")
    public void validCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        ImageCodeUtil.generate(request, response, session);
        String code = (String) session.getAttribute("imageCode");
        log.info("imageCode:{}", code);
    }


}