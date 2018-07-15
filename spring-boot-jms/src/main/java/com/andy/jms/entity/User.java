package com.andy.jms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;

    private String account;

    private String password;

    private Date birthday;

    private Double salary;

    private Date createTime;

    private Boolean deleted;

}
