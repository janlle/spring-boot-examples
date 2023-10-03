package com.leone.boot.security.service;

import com.leone.boot.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author leone
 * @since 2018-04-10
 **/
public interface IUserService extends UserDetailsService {

    User findByAccount(String account);

}
