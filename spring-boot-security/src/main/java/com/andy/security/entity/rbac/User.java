package com.andy.security.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 *
 * @author: lyon
 * @since: 2018-04-19
 **/
@Data
@Entity
@ApiModel("用户实体")
@Table(name = "sys_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '账号'")
    private String account;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '盐'")
    private String salt;

    @CreatedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @LastModifiedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Date updateTime;

    @Column(columnDefinition = "bit NOT NULL COMMENT '是否可用'")
    private Boolean disable;

    @Column(columnDefinition = "bit NOT NULL COMMENT '是否删除'")
    private Boolean deleted;

//    @ApiModelProperty("角色集合")
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "t_user_role",
//            joinColumns = {@JoinColumn(name = "uId", referencedColumnName = "userId")},
//            inverseJoinColumns = {@JoinColumn(name = "rId", referencedColumnName = "roleId")})
//    private Set<Role> roles = new HashSet<>();

}