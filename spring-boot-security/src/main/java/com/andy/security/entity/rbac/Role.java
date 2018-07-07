package com.andy.security.entity.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 *
 * @author: Mr.ruoLin
 * @createBy: 2018-04-19
 **/
@Data
@Entity
@ApiModel("角色实体")
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '角色名称'")
    private String roleName;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '角色的详情'")
    private String description;

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

    @ApiModelProperty("对应的权限集合")
//    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions = new HashSet<>();

    @ApiModelProperty("对应的角色集合")
//    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}