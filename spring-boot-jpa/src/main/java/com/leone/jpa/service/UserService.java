package com.leone.jpa.service;

import com.leone.jpa.entity.User;
import com.leone.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *
 * @author leone
 * @since 2018-05-24
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param user
     * @return
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * @param user
     */
    public User update(User user) {
        return userRepository.save(user);
    }

}
