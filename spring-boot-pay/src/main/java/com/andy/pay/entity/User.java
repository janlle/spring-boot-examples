package com.andy.pay.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("")
public class User {

    private Long id;

    private String userId;

    private String siteId;

    private String openid;

    private String nickname;

    private String username;

    private String password;

    private Byte age;

    private Byte sex;

    private String email;

    private String phone;

    private String faceUrl;

    private Date birthday;

    private String description;

    private Byte status;

    private Date createTime;

    private Date updateTime;


}