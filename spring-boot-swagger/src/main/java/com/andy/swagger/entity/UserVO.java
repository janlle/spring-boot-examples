package com.andy.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Leone
 * @since 2018-07-12 22:42
 **/
@Data
@ApiModel
public class UserVO {

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "工资")
    private Double salary;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

}
