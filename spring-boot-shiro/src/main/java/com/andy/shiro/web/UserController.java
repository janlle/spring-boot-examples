package com.andy.shiro.web;

import com.andy.shiro.entity.rbac.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/")
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
    @RequestMapping("/admin")
    public String admin(){
        return "home";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        User user = new User();
        user.setUsername("james");
        modelMap.put("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        log.info("跳转到login页面");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password")String password,
                            HttpSession session, ModelMap modelMap){
        log.info("用户的登录的控制器！");
        UsernamePasswordToken loginToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginToken);
            User user = (User) subject.getPrincipal();
            log.info("user:{}", user);
            session.setAttribute("user", user);
            modelMap.put("user", user);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }


}