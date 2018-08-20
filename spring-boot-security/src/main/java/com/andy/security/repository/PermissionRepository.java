package com.andy.security.repository;

import com.andy.security.entity.rbac.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Leone
 * @since: 2018-07-07 14:39
 **/
public interface PermissionRepository extends JpaRepository<Permission, Long>,JpaSpecificationExecutor<Permission> {
}
