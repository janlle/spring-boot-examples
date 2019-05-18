package com.leone.boot.shiro.mapper;

import com.leone.boot.shiro.entity.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}