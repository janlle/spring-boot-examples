package com.leone.boot.shiro.config;


import com.leone.boot.shiro.entity.Permission;
import com.leone.boot.shiro.entity.Role;
import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leone
 * @since 2018-04-21
 **/
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入 AuthorizingRealm 认证方法...");
        String name = token.getPrincipal().toString();
        User user = userService.findByAccount(name);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入AuthorizingRealm授权方法...");
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();

        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();

        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roleList.add(role.getRole());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getPermission());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleList);
        log.info("user: {} roles: {} permissions: {}", user.getAccount(), roleList, permissionList);
        return info;
    }

}
