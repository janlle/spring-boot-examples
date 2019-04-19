package com.leone.shiro.entity.rbac;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 *
 * @author Leone
 * @since 2018-04-19
 **/
@Data
public class Role {

    private Long roleId;

    private String roleName;

    private String description;

    private Boolean deleted;

    private Date createTime;

    private Set<Permission> permissions = new HashSet<>();

}