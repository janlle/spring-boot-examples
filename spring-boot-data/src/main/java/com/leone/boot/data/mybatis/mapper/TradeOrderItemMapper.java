package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.data.mybatis.entity.TradeOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TradeOrderItem row);

    int insertSelective(TradeOrderItem row);

    TradeOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrderItem row);

    int updateByPrimaryKey(TradeOrderItem row);
}