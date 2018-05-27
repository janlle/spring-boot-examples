package com.andy.shiro.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户实体")
public class User implements Serializable {

    @ApiModelProperty("用户id")
    private Integer     userId;

    @ApiModelProperty("用户姓名")
    private String      username;

    @ApiModelProperty("用户密码")
    private String      password;

    @ApiModelProperty("昵称")
    private String      nickname;

    @ApiModelProperty("盐")
    private String      salt;

    @ApiModelProperty("用户状态")
    private String      state;

    @ApiModelProperty("角色集合")
    private Set<Role>   roles = new HashSet<>();

 }