package com.andy.security.repository;

import com.andy.security.entity.rbac.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: lyon
 * @createBy: 2018-07-07 14:39
 **/
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findFirstByAccount(String account);

}
