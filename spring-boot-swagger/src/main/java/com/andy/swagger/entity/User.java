package com.andy.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Leone
 * @since: 2018-07-12 22:41
 **/
@Data
@Entity
public class User {

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

    @CreatedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bit NOT NULL COMMENT '是否删除'")
    private Boolean deleted;

}
