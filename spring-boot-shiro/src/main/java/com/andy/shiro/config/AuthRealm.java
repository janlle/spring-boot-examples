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
 * @author: Mr.lyon
 * @createBy: 2018-04-21 16:51
 **/
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    Map<String, String> map = new HashMap<>();

    {
        map.put("james", "james");
        map.put("kobe", "kobe");
    }

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
//        User user = userService.findByUsername(username);
        String pwd = getPassword(username);
        return new SimpleAuthenticationInfo(username, pwd, this.getClass().getName());
//        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    // 授权时调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入AuthorizingRealm授权方法...");

//        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
//        List<String> permissionList = new ArrayList<>();
//        List<String> roleList = new ArrayList<>();
//        Set<Role> roleSet = user.getRoles();
//        if (CollectionUtils.isNotEmpty(roleSet)) {
//            for (Role role : roleSet) {
//                roleList.add(role.getRole());
//                Set<Permission> permissionSet = role.getPermissions();
//                if (CollectionUtils.isNotEmpty(permissionSet)) {
//                    for (Permission permission : permissionSet) {
//                        permissionList.add(permission.getPermission());
//                    }
//                }
//            }
//        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermissions(permissionList);
//        info.addRoles(roleList);
        info.setRoles(getRole());
        info.setStringPermissions(getPermission());
//        log.info("permissionList:{}", permissionList);
//        log.info("roleList:{}", roleList);
        return info;
    }

    private Set<String> getPermission() {
        Set<String> set = new HashSet<>();
        set.add("user:update");
        set.add("user:delete");
        return set;
    }


    private String getPassword(String username) {

        return map.get(username);
    }

    private Set<String> getRole() {
        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("user");
        return set;
    }


}
