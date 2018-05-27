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
public class User implements Serializable {

    private Integer     userId;

    private String      username;

    private String      password;

    private String      nickname;

    private String      salt;

    private String      state;

    private Set<Role>   roles = new HashSet<>();

 }