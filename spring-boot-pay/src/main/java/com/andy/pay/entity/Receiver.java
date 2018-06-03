package com.andy.pay.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("")
public class Receiver {

    private Long id;

    private String receiverId;

    private String userId;

    private String receiverName;

    private String receiverPhone;

    private Integer receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private Integer status;

    private Integer isDefault;

    private String receiverZip;

    private Date createTime;

    private Date updateTime;


}