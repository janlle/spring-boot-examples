package com.andy.pay.pojos.entity;

import com.andy.pay.common.IdEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("")
public class Payment extends IdEntity {

    private String paymentId;

    private String orderId;

    private Integer payType;

    private String userId;

    private String tradeNo;

    private String outTradeNo;

    private String receiverId;

    private Long totalFee;

    private Date updateTime;

}