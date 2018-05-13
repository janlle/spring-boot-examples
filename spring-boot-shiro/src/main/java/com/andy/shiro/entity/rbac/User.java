package com.andy.shiro.entity.rbac;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
//@Table(name = "sys_user")
//@Entity
public class User implements Serializable {

//    @Id
//    @GeneratedValue
    private Integer uid;

    private String username;

    private String password;

    private String name;

    private String salt;

    private String state;

    //对应的角色列表
//    private Set<Role> roles = new HashSet<>();

 }