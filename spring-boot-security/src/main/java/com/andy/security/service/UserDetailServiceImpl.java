package com.andy.security.service;

import com.andy.security.entity.rbac.Permission;
import com.andy.security.entity.rbac.User;
import com.andy.security.mapper.PermissionMapper;
import com.andy.security.mapper.UserMapper;
import com.andy.security.repository.PermissionRepository;
import com.andy.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Leone
 * @since 2018-04-10 17:07
 **/
@Slf4j
@Component
public class UserDetailServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private PermissionMapper permissionMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = passwordEncoder.encode("andy");
        log.info("登录用户是:{}数据库的密码是:{}", username, password);

        return new SocialUser(username, password, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }



}
