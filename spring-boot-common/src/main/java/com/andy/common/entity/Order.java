package com.andy.common.entity;

import java.util.Date;

/**
 * @author Leone
 * @since 2018-06-21
 **/
public class Order {

    private Long orderId;

    private Long userId;

    private Integer totalAmount;

    private String remark;

    private String tradeNo;

    private Date createTime;

    private Date payTime;

    private boolean deleted;

    public Order() {
    }

    public Order(Long orderId, Long userId, Integer totalAmount, String remark, String tradeNo, Date createTime, Date payTime, boolean deleted) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.remark = remark;
        this.tradeNo = tradeNo;
        this.createTime = createTime;
        this.payTime = payTime;
        this.deleted = deleted;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
