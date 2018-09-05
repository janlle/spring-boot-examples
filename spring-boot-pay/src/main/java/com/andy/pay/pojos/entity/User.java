package com.andy.pay.pojos.entity;

import com.andy.pay.common.IdEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author Leone
 * @since: 2018/6/3 20:09
 **/
@Data
@ApiModel("用户实体")
public class User extends IdEntity {

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

    private Date updateTime;

}