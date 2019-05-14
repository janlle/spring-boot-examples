package com.leone.boot.jwt.repository;

import com.leone.boot.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leone
 * @since 2018-04-15
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccount(String email);

}
