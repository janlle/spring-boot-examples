package com.andy.shiro.entity.rbac;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
public class User implements Serializable {

    private Integer uid;

    private String username;

    private String password;

    //对应的角色列表
    private Set<Role> roles = new HashSet<>();

 }