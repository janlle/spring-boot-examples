package com.andy.pay.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("")
public class Payment {

    private Long id;

    private String paymentId;

    private String orderId;

    private Integer payType;

    private String userId;

    private String tradeNo;

    private String outTradeNo;

    private String receiverId;

    private Long totalFee;

    private Date createTime;

    private Date updateTime;


}