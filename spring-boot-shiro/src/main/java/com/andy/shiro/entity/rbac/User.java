package com.andy.shiro.entity.rbac;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 *
 * @author Leone
 * @since: 2018-04-19
 **/
@Data
public class User implements Serializable {

    private Long userId;

    private String account;

    private String password;

    private String email;

    private String salt;

    private Boolean disable;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    private Set<Role> roles = new HashSet<>();

}