package com.leone.boot.security.service;

import com.leone.boot.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-30
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String account, String password) {
        return ObjectUtils.isEmpty(userRepository.login(account, password));
    }

}
