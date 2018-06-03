package com.andy.pay.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("")
public class Category implements Serializable {

    private Long id;

    private String categoryId;

    private String categoryName;

    private Integer categoryType;

    private String parentId;

    private Integer status;

    private Integer isParent;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

}