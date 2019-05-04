package com.leone.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author leone
 * @since 2018-07-12
 **/
@Data
@ApiModel
public class UserVO {

    @ApiModelProperty(value = "主键",example = "1200L")
    private Long userId;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("自我介绍")
    private String description;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
