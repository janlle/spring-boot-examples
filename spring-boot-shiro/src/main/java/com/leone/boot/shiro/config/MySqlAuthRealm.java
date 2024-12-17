package com.leone.boot.shiro.config;


import com.leone.boot.shiro.entity.SysPermission;
import com.leone.boot.shiro.entity.SysRole;
import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leone
 * @since 2018-04-21
 **/
@Component
public class MySqlAuthRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MySqlAuthRealm.class);

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
        log.info("AuthorizingRealm 认证...");
        String name = token.getPrincipal().toString();
        SysUser user = userService.findAllPermissionByAccount(name);
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
        log.info("AuthorizingRealm 授权...");
        SysUser user = (SysUser) principalCollection.fromRealm(this.getClass().getName()).iterator().next();

        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();

        Set<SysRole> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (SysRole role : roleSet) {
                roleList.add(role.getRoleName());
                Set<SysPermission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (SysPermission permission : permissionSet) {
                        permissionList.add(permission.getPermissionName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleList);
        log.info("user: {} roles: {} permissions: {}", user.getUserId(), roleList, permissionList);
        return info;
    }

}
