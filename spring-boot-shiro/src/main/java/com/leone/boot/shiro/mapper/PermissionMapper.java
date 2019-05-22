package com.leone.boot.shiro.mapper;

import com.leone.boot.shiro.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}