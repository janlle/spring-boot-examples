package com.leone.boot.mvc.config;


import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义权限认证接口扩展，Sa-Token 将从此实现类获取每个账号拥有的权限码
 *
 * @author leone
 * @since 2024-11-13
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<>();
        list.add("user.list");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        list.add("user.del");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }


}
