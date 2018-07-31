package com.andy.aop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: lyon
 * @since: 2018-06-21 00:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '账号'")
    private String account;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "datetime NOT NULL COMMENT '生日'")
    private Date birthday;

    @Column(columnDefinition = "double NOT NULL COMMENT '工资'")
    private Double salary;

    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bit NOT NULL COMMENT '是否删除'")
    private Boolean deleted;

}
