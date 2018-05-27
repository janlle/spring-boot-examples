package com.andy.shiro.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("角色实体")
public class Role {

    @ApiModelProperty("角色id")
    private Integer     roleId;

    @ApiModelProperty("角色名称")
    private String      role;

    @ApiModelProperty("是否可用")
    private Boolean     available;

    @ApiModelProperty("角色的详情")
    private String      description;

    @ApiModelProperty("对应的权限集合")
    private Set<Permission> permissions = new HashSet<>();

    @ApiModelProperty("对应的角色集合")
    private Set<User> users = new HashSet<>();


}