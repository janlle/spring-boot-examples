package com.leone.boot.security.repository;

import com.leone.boot.security.entity.rbac.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author leone
 * @since 2018-07-07
 **/
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findFirstByAccount(String account);

}
