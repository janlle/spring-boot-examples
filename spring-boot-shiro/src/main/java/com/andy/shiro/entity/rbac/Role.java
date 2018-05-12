package com.andy.shiro.entity.rbac;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
public class Role {

    private Integer rid;

    private String name;

    //对应的权限集合
    private Set<Permission> permissions = new HashSet<>();

    //对应的角色集合
    private Set<User> users = new HashSet<>();


}