package com.andy.jwt.repository;

import com.andy.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leone
 * @since: 2018-04-15 09:50
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserById(Long id);

}
