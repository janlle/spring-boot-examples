package com.andy.shiro.entity.rbac;

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
//@Table(name = "sys_permission")
//@Entity
public class Permission implements Serializable {

//    @Id
//    @GeneratedValue
    private Integer     permissionId;

    private String      name;

    private String      url;

    private String      available;

    private Integer     parentId;

    private String      resourceType;

 }