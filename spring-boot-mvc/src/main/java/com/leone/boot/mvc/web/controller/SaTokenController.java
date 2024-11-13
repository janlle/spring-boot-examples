package com.leone.boot.mvc.web.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.leone.boot.common.Result;
import com.leone.boot.mvc.shiro.service.ShiroTokenService;
import com.leone.boot.mvc.shiro.service.UserHelper;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p> Sa token 测试控制器
 *
 * @author leone
 * @since 2024-11-13
 **/
@RestController("/sa")
public class SaTokenController {

    /**
     * 访问 home 页，登录后才能访问
     */
    @SaCheckLogin
    @GetMapping("/home")
    public Result<Object> test() {
        return Result.success(null);
    }

    @RequestMapping("login")
    public SaResult login(String name, String pwd) {
        if ("james".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    @GetMapping("/logout")
    public Result<Object> logout(String userId) {
        return Result.success(null);
    }


    /*
     * 前提1：首先调用登录接口进行登录
     * http://localhost:8081/doLogin?name=james&pwd=123456
     *
     * 前提2：项目在配置类中注册拦截器 SaInterceptor ，此拦截器将打开注解鉴权功能
     *
     * 前提3：项目实现了 StpInterface 接口，此接口会告诉框架指定账号拥有哪些权限码
     *
     * 然后我们就可以使用以下示例中的代码进行注解鉴权了
     */

    // 权限校验 http://localhost:8081/at-check/checkPermission
    // 只有具有 user.add 权限的账号才可以进入方法
    @SaCheckPermission("user.add")
    @RequestMapping("checkPermission")
    public SaResult checkPermission() {
        return SaResult.ok();
    }

    // 角色校验 http://localhost:8081/at-check/checkRole
    //只有具有 super-admin 角色的账号才可以进入方法
    @SaCheckRole("super-admin")
    @RequestMapping("checkRole")
    public SaResult checkRole() {
        return SaResult.ok();
    }


}