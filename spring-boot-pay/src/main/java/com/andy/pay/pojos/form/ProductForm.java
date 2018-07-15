package com.andy.pay.pojos.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("商品实体")
public class ProductForm {

    @ApiModelProperty("商品id")
    private String productId;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品价格单位分")
    private Long price;

    @ApiModelProperty("商品条码")
    private String barcode;

    @ApiModelProperty("商品数量")
    private Integer count;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("图片url")
    private String imageUrl;

    @ApiModelProperty("小图")
    private String icon;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

}