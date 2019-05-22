package com.leone.boot.security.repository;

import com.leone.boot.security.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author leone
 * @since 2018-07-07
 **/
public interface PermissionRepository extends JpaRepository<Permission, Long>,JpaSpecificationExecutor<Permission> {
}
