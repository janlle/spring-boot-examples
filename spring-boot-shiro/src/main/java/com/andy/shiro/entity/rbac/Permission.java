package com.andy.shiro.entity.rbac;

import lombok.Data;

import java.io.Serializable;


/**
 * 权限信息
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
public class Permission implements Serializable {

    private Integer pid;

    private String name;

    private String url;


 }