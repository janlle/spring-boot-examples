package com.andy.security.repository;

import com.andy.security.entity.rbac.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: lyon
 * @since: 2018-07-07 14:39
 **/
public interface RoleRepository extends JpaRepository<Role, Long>,JpaSpecificationExecutor<Role> {
}
