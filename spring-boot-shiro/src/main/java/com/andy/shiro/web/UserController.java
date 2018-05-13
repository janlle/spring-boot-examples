package com.andy.shiro.web;

import com.andy.shiro.entity.rbac.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shiro")
public class UserController {

    /**
     * 查询用户
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel(){
        return "userInfoDel";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/userUpdate")
    @RequiresPermissions("userInfo:update")
    public String userUpdate(){
        return "userInfoDel";
    }

    @ResponseBody
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password")String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }


}