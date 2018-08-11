package com.andy.data.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    public User() {
    }

    public User(String account, String password, String email, Integer age, Date createTime, Boolean deleted) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.age = age;
        this.createTime = createTime;
        this.deleted = deleted;
    }

    public User(Long userId, String account, String password, String email, Integer age, Date createTime, Boolean deleted) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.email = email;
        this.age = age;
        this.createTime = createTime;
        this.deleted = deleted;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '账号'")
    private String account;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '邮箱'")
    private String email;

    @Column(columnDefinition = "int(11) NOT NULL COMMENT '年龄'")
    private Integer age;

    @CreatedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bit NOT NULL COMMENT '是否删除'")
    private Boolean deleted;


}
