package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.data.mybatis.entity.TradeOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TradeOrderItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TradeOrderItem row);

    int insertSelective(TradeOrderItem row);

    TradeOrderItem selectByPrimaryKey(Long id);

    @Select("select * from trade_order_item where order_id = #{order_id}")
    @ResultMap("BaseResultMap")
    TradeOrderItem selectByOrderId(@Param("order_id") String orderId);

    int updateByPrimaryKeySelective(TradeOrderItem row);

    int updateByPrimaryKey(TradeOrderItem row);
}