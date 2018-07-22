package com.andy.shiro.entity.rbac;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 *
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
@ApiModel("用户实体")
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