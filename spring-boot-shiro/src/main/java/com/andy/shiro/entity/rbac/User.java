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
 * @since 2018-04-19
 **/
@Data
public class User implements Serializable {

    private Long userId;

    private String account;

    private String password;

    private String description;

    private Integer age;

    private Date createTime;

    private boolean deleted;

    private Set<Role> roles = new HashSet<>();

}