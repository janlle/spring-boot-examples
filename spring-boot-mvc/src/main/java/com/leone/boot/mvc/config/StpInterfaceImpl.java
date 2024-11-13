package com.leone.boot.mvc.config;


import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义权限认证接口扩展，Sa-Token 将从此实现类获取每个账号拥有的权限码
 *
 * @author leone
 * @since 2024-11-13
 **/
//@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        // list.add("user.delete");
        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }

    // 只要具有其中一个权限即可通过校验
    @RequestMapping("atJurOr")
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }

    // 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

    // 在当前会话完成二级认证  ---- http://localhost:8081/at/openSafe
    @RequestMapping("openSafe")
    public SaResult openSafe() {
        StpUtil.openSafe(200); // 打开二级认证，有效期为200秒
        return SaResult.ok();
    }

    // 通过二级认证后，才可以进入  ---- http://localhost:8081/at/checkSafe
    @SaCheckSafe
    @RequestMapping("checkSafe")
    public SaResult checkSafe() {
        return SaResult.ok();
    }

    // 通过Basic认证后才可以进入  ---- http://localhost:8081/at/checkBasic
    @SaCheckHttpBasic(account = "sa:123456")
    @RequestMapping("checkBasic")
    public SaResult checkBasic() {
        return SaResult.ok();
    }

    // 只有当前服务没有禁用 comment 服务时，才能够进入方法  ---- http://localhost:8081/at/comment
    @SaCheckDisable("comment")
    @RequestMapping("comment")
    public SaResult comment() {
        return SaResult.ok();
    }

    // 此接口加上了 @SaIgnore 可以游客访问
    @SaIgnore
    @RequestMapping("getList")
    public SaResult getList() {
        // ...
        return SaResult.ok();
    }



}
