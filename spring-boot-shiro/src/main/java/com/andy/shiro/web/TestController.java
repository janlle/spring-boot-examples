package com.andy.shiro.web;//package com.andy.shiro.web;
//
//import com.andy.shiro.entity.rbac.User;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
///**
// * @author: Mr.ruoLin
// * @createBy: 2018-04-21 18:40
// **/
//@Controller
//public class TestController {
//
//    @RequestMapping("/index")
//    public String index(){
//        return "index";
//    }
//
//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping("/loginUser")
//    public String loginUser(@RequestParam("username") String username,
//                            @RequestParam("password")String password,
//                            HttpSession session){
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//            User user = (User) subject.getPrincipal();
//            session.setAttribute("user", user);
//            return "index";
//        } catch (Exception e) {
//            return "login";
//        }
//    }
//
//}
