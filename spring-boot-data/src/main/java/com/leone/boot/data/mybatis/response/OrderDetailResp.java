package com.leone.boot.data.mybatis.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leone.boot.data.mybatis.entity.TradeOrder;
import com.leone.boot.data.mybatis.entity.TradeOrderItem;

@JsonIgnoreProperties(value = "handler")
public class OrderDetailResp extends TradeOrder {

    private TradeOrderItem itemList;

    public TradeOrderItem getItemList() {
        return itemList;
    }

    public void setItemList(TradeOrderItem itemList) {
        this.itemList = itemList;
    }
}
