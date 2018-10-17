package com.andy.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Leone
 * @since 2018-07-12
 **/
@Data
@ApiModel
public class UserDTO {

    @ApiModelProperty("用户账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("工资")
    private Double salary;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;

}
