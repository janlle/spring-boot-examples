package com.andy.shiro.web;

import com.andy.shiro.exception.IncorrectCaptchaException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

// 只用同时具有permission:view和permission:aix权限才能访问
//@RequiresPermissions(value={"permission:view","permission:aix"}, logical= Logical.AND)
//@RequiresPermissions(value={"permission:view","permission:aix"}, logical= Logical.OR)一个就行
@Controller(value = "LoginController")
public class LoginController {

    // 登录提交地址和配置的loginurl一致
//    @PostMapping(value = "/login")
//    public String login(HttpServletRequest request, Map<String, Object> map) {
//
//        // 登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.
//        Object exception = request.getAttribute("shiroLoginFailure");
//        String msg = "";
//        if (exception != null){
//            if (UnknownAccountException.class.isInstance(exception)) {
//                msg = "提示->账号不存在";
//            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
//                msg = "提示->密码不正确";
//            } else if (IncorrectCaptchaException.class.isInstance(exception)) {
//                msg = "提示->验证码不正确";
//            } else {
//                msg = "提示->未知错误";
//            }
//            map.put("msg", msg);
//            return "login";
//        }
//        //如果已经登录，直接跳转主页面
//        return "index";
//    }

}
