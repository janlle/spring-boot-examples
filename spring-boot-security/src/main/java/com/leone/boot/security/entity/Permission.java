package com.leone.boot.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * 权限信息
 *
 * @author leone
 * @since 2018-04-19
 **/
@Entity
@Table(name = "sys_permission")
@Proxy(lazy = false)
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '权限名称'")
    private String permission;

    @Column(columnDefinition = "varchar(128) NOT NULL COMMENT '资源路径'")
    private String url;

    @Column(columnDefinition = "integer NOT NULL COMMENT '父编号'")
    private Long parentId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '描述'")
    private String description;

    @CreatedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bit NOT NULL COMMENT '状态1.正常，2.禁用，3.冻结，4.删除'")
    private Integer status;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions", fetch=FetchType.EAGER)
    private Set<Role> roles;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}