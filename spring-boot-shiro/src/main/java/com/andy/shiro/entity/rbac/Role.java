package com.andy.shiro.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 *
 * @author: Mr.ruoLin
 * @since: 2018-04-19
 **/
@Data
@ApiModel("角色实体")
public class Role {

    private Long roleId;

    private String roleName;

    private String description;

    private Boolean disable;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    private Set<Permission> permissions = new HashSet<>();

}