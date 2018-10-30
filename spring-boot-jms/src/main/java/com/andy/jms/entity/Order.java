package com.andy.jms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-27
 **/
@Data
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId;

    private Long addressId;

    private Integer postFee;

    private Integer totalFee;

    private Byte status;

    private String remark;

    private String outTradeNo;

    private String transactionId;

    private String createIp;

    private String messageId;

    private Date createTime;

    private Date finishTime;

    private Date payTime;

}