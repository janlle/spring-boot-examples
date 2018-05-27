package com.andy.shiro.entity.rbac;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
public class Role {

    private Integer     roleId;

    private String      name;

    private String      available;

    private String      description;

    //对应的权限集合
    private Set<Permission> permissions = new HashSet<>();

    //对应的角色集合
    private Set<User> users = new HashSet<>();


}