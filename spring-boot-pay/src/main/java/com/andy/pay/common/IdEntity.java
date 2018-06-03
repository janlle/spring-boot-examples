package com.andy.pay.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-03 20:06
 **/
@Data
@ApiModel("所有实体的父类")
public class IdEntity implements Serializable {

    @ApiModelProperty("主键")
    public long id;

    @ApiModelProperty("创建时间")
    public Date createTime;

}
