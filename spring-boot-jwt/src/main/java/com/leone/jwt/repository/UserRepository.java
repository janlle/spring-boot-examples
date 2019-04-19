package com.leone.jwt.repository;

import com.leone.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leone
 * @since 2018-04-15
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccount(String email);

}
