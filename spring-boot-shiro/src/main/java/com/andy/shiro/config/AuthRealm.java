package com.andy.shiro.config;

import com.andy.shiro.entity.rbac.Permission;
import com.andy.shiro.entity.rbac.Role;
import com.andy.shiro.entity.rbac.User;
import com.andy.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author: lyon
 * @createBy: 2018-04-21 16:51
 **/
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 认证时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入AuthorizingRealm认证方法...");
        String name = (String) token.getPrincipal();

        // 获取用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = usernamePasswordToken.getPassword().toString();
        // 查询数据库
        User user = userService.getByAccount(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    // 授权时调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入AuthorizingRealm授权方法...");

        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roleList.add(role.getRoleName());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getPermissionName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleList);
        log.info("permissionList:{}", permissionList);
        log.info("roleList:{}", roleList);
        return info;
    }

}
