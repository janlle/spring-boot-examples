package com.andy.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: Mr.lyon
 * @createBy: 2018-07-12 22:42
 **/
@Data
@ApiModel
public class UserForm {

    @ApiModelProperty(value = "用户账号", name = "accountName", allowableValues = "hello", access = "access",
            notes = "notes", dataType = "dataType", required = true, position = 2, hidden = false, example = "example", readOnly = true
            , reference = "reference", allowEmptyValue = true
    )
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
