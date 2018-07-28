package com.andy.shiro.entity.rbac;

import com.andy.shiro.common.enums.ResourceType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 权限信息
 *
 * @author: lyon
 * @createBy: 2018-04-19
 **/
@Data
@ApiModel("权限实体")
public class Permission implements Serializable {

    private Long permissionId;

    private String permissionName;

    private String url;

    private Long parentId;

    private String description;

    private ResourceType type;

    private Date createTime;

    private Date updateTime;

    private Boolean disable;

    private Boolean deleted;

}