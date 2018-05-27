package com.andy.shiro.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Target;


/**
 * 权限信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
@ApiModel("权限实体")
public class Permission implements Serializable {

    @ApiModelProperty("主键")
    private Integer     permissionId;

    @ApiModelProperty("名称")
    private String      name;

    @ApiModelProperty("资源路径")
    private String      url;

    @ApiModelProperty("是否可用")
    private String      available;

    @ApiModelProperty("父编号")
    private Integer     parentId;

    @ApiModelProperty("资源类型，[menu|button]")
    private String      resourceType;

    @ApiModelProperty("父编号")
    private String      permission;

 }