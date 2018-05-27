package com.andy.shiro.web;

import com.andy.shiro.config.ImageCodeUtil;
import com.andy.shiro.entity.rbac.User;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Slf4j
@Controller
@RequestMapping
public class UserController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 查询用户
     */

    @RequestMapping("/user/list")//符合user:view或user:add权限要求即可
    @RequiresPermissions(value={"user:add","user:select"},logical= Logical.OR)
    public String user(){
        return "user";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/user/add")//符合user:view和user:add权限要求
    @RequiresPermissions(value={"user:select","user:add"},logical= Logical.AND)
    public String userAdd(){
        return "userAdd";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/user/delete")
    @RequiresPermissions("user:delete")
    public String userDel(){
        return "userDel";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/user/update")
    @RequiresPermissions("user:update")
    public String userUpdate(){
        return "userUpdate";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(){
        return "home";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        return "index";
    }
    //登录页(shiro配置需要两个/login 接口,一个是get用来获取登陆页面,一个用post用于登录,这是一个坑)
    @GetMapping("/login")
    public String login() {
        log.info("跳转到login页面控制器！");
        return "login";
    }

//    @ResponseStatus(HttpStatus.GONE)
    @PostMapping(value = "/login")
    public String loginUser(String username, String password, HttpSession session, ModelMap modelMap){
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


    @RequestMapping("/imageCode")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String code = defaultKaptcha.createText();
            request.getSession().setAttribute("imageCode", code);
            BufferedImage challenge = defaultKaptcha.createImage(code);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(captchaChallengeAsJpeg);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("/image")
    public void validCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        ImageCodeUtil.generate(request, response, session);
        String code = (String) session.getAttribute("imageCode");
        log.info("imageCode:{}", code);
    }


}