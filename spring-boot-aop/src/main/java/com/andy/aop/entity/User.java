package com.andy.aop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-21 00:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private long id;

    private String username;

    private String email;

    private Double salary;

    private Date birthday;

}
