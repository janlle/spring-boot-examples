package com.leone.boot.data.jpa.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-19
 **/
@Entity
@Table(name = "t_user_address")
public class UserAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(128) not null comment '用户表用户ID'")
    private String userId;

    @Column(columnDefinition = "varchar(128) not null comment '收货人姓名'")
    private String username;

    @Column(columnDefinition = "varchar(11) not null comment '收货人手机号'")
    private String telephone;

    @Column(columnDefinition = "varchar(255) comment '详细地址'")
    private String specificsAddress;

    @Column(columnDefinition = "int(2) not null comment '是否是默认地址'")
    private Integer defaultAddress;

    @Column(columnDefinition = "int(2) not null comment '是否删除'")
    private Integer deleted;

    @org.springframework.data.annotation.Version
    @Column(columnDefinition = "int(2) not null comment '乐观锁版本号'")
    private Integer lockVersion;

    @CreatedDate
    @Column(columnDefinition = "timestamp not null default current_timestamp comment '创建时间'")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(columnDefinition = "timestamp not null default current_timestamp comment '更新时间'")
    private Date gmtModified;

    public UserAddress() {
    }

    public UserAddress(Long id, String userId, String username, String telephone, String specificsAddress, Integer defaultAddress, Integer deleted, Integer lockVersion, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.telephone = telephone;
        this.specificsAddress = specificsAddress;
        this.defaultAddress = defaultAddress;
        this.deleted = deleted;
        this.lockVersion = lockVersion;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecificsAddress() {
        return specificsAddress;
    }

    public void setSpecificsAddress(String specificsAddress) {
        this.specificsAddress = specificsAddress;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
          "id=" + id +
          ", userId='" + userId + '\'' +
          ", username='" + username + '\'' +
          ", telephone='" + telephone + '\'' +
          ", specificsAddress='" + specificsAddress + '\'' +
          ", defaultAddress=" + defaultAddress +
          ", deleted=" + deleted +
          ", lockVersion=" + lockVersion +
          ", gmtCreate=" + gmtCreate +
          ", gmtModified=" + gmtModified +
          '}';
    }
}