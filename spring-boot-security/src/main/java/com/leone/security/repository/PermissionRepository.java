package com.leone.security.repository;

import com.leone.security.entity.rbac.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Leone
 * @since 2018-07-07
 **/
public interface PermissionRepository extends JpaRepository<Permission, Long>,JpaSpecificationExecutor<Permission> {
}
