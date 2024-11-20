package com.leone.boot.data.mybatis.entity;

import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-19
 **/
@Table
public class TradeOrderItem extends BaseEntity {

    private String tradeOrderItemId;

    private String orderId;

    private String userId;

    private String goodsId;

    private String goodsName;

    private Integer goodsType;

    private Integer orderState;

    private String goodsPic;

    private BigDecimal itemPrice;

    private Integer itemCount;

    public String getTradeOrderItemId() {
        return tradeOrderItemId;
    }

    public void setTradeOrderItemId(String tradeOrderItemId) {
        this.tradeOrderItemId = tradeOrderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}
