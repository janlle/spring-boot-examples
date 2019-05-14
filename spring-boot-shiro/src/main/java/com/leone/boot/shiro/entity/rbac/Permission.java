package com.leone.boot.shiro.entity.rbac;

import com.leone.boot.shiro.common.enums.ResourceType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 权限信息
 *
 * @author leone
 * @since 2018-04-19
 **/
@Data
public class Permission implements Serializable {

    private Long permissionId;

    private String permissionName;

    private String url;

    private Long parentId;

    private String description;

    private ResourceType type;

    private Date createTime;

    private Boolean deleted;

}