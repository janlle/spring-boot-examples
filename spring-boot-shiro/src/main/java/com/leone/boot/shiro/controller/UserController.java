package com.leone.boot.shiro.controller;

import com.leone.boot.shiro.utils.ImageCodeUtil;
import com.leone.boot.shiro.utils.UserHelper;
import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p> shiro 配置需要两个 /login 接口,一个是get用来获取登陆页面,一个用post用于登录的
 *
 * @author leone
 * @since 2018-05-18
 **/
@Slf4j
@RestController
@RequestMapping("/api/shiro/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String account, @RequestParam String password, HttpSession session, @RequestHeader("Host") String host) {
        password = new Md5Hash("admin").toString();
        User login = userService.login(account, password);
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


    /**
     * 添加用户
     */
    @RequestMapping("/admin/add")//符合admin:view和admin:add权限要求
    @RequiresRoles(value={"admin","manager"},logical=Logical.AND)
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 查询用户
     */
    @RequestMapping("/admin/list")
    @RequiresRoles(value={"leader","admin","manager"},logical=Logical.OR)
    public String user(){
        return "user";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/admin/delete")
    @RequiresRoles(value={"admin"})
    public String userDel(){
        return "userDel";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/admin/update")
    @RequiresRoles(value={"admin","manager"},logical=Logical.OR)
    public String userUpdate(){
        return "userUpdate";
    }


}