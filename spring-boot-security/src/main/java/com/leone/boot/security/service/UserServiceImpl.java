package com.leone.boot.security.service;

import com.leone.boot.security.entity.Role;
import com.leone.boot.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-30
 **/
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean login(String account, String password) {
        return ObjectUtils.isEmpty(userRepository.login(account, password));
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.leone.boot.security.entity.User user = userRepository.findFirstByAccount(username);

        String password = passwordEncoder.encode(user.getPassword());
        log.info("登录用户是: {} 数据库密码是: {}", username, password);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(username, password, true, true, true, true, authorities);
    }

    public com.leone.boot.security.entity.User findByAccount(String account) {
        log.info("登录账号: {} ", account);
        return userRepository.findFirstByAccount(account);
    }


}
