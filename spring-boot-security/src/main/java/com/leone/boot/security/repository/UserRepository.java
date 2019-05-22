package com.leone.boot.security.repository;

import com.leone.boot.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author leone
 * @since 2018-07-07
 **/
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findFirstByAccount(String account);

    @Query(nativeQuery = true, value = "select * from sys_user where username = ? and password = ?")
    User login(String account, String password);
}
