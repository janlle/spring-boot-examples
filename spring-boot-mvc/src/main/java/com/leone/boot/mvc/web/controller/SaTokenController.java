package com.leone.boot.mvc.web.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> Sa token 测试控制器
 *
 * @author leone
 * @since 2024-11-13
 **/
@RestController
@RequestMapping("/sa")
public class SaTokenController {

    // 此接口加上了 @SaIgnore 可以游客访问
    @SaIgnore
    @RequestMapping("/over-view")
    public SaResult overView() {
        return SaResult.ok();
    }


    @RequestMapping("/login")
    public SaResult login(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
        if ("james".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            List<String> list = StpUtil.getPermissionList();
            list.addAll(StpUtil.getRoleList());

            return SaResult.data(list);
        }
        return SaResult.error("登录失败");
    }

    /**
     * 访问 home 页，登录后才能访问
     */
    @SaCheckLogin
    @GetMapping("/home")
    public SaResult home() {
        return SaResult.error("home 页面");
    }

    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout(10001);
        return SaResult.ok("登出成功");
    }

    // 通过Basic认证后才可以进入 http://localhost:8080/check-basic
    @SaCheckHttpBasic(account = "james:123456")
    @GetMapping("/check-basic")
    public SaResult checkBasic() {
        return SaResult.ok();
    }

    // 在当前会话完成二级认证 http://localhost:8080/open-safe
    @RequestMapping("/open-safe")
    public SaResult openSafe() {
        // 打开二级认证，有效期为200秒
        StpUtil.openSafe(200);
        return SaResult.ok();
    }

    // 通过二级认证后，才可以进入 http://localhost:8081/at/checkSafe
    @SaCheckSafe
    @RequestMapping("/check-safe")
    public SaResult checkSafe() {
        return SaResult.ok();
    }

    /*
     * 前提1：首先调用登录接口进行登录
     * http://localhost:8080/login?name=james&pwd=123456
     *
     * 前提2：项目在配置类中注册拦截器 SaInterceptor ，此拦截器将打开注解鉴权功能
     *
     * 前提3：项目实现了 StpInterface 接口，此接口会告诉框架指定账号拥有哪些权限码
     *
     * 然后我们就可以使用以下示例中的代码进行注解鉴权了
     */

    // 只要具有其中一个权限即可通过校验
    @RequestMapping("/user-add")
    @SaCheckPermission(value = {"user.add", "user.del"}, mode = SaMode.OR)
    public SaResult userAdd() {
        return SaResult.data("添加成功");
    }

    // 角色权限双重 or校验：具备指定权限或者指定角色即可通过校验
    @RequestMapping("/user-del")
    @SaCheckPermission(value = "user.del", orRole = "admin")
    public SaResult userDel() {
        return SaResult.data("删除成功");
    }


}