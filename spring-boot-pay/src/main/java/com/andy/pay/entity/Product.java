package com.andy.pay.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("")
public class Product {

    private Long id;

    private String productId;

    private Integer categoryId;

    private String name;

    private Long price;

    private String barcode;

    private Integer number;

    private String description;

    private String imageUrl;

    private String icon;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}