package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.data.mybatis.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderMapper {
    
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder row);

    int insertSelective(TradeOrder row);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder row);

    int updateByPrimaryKey(TradeOrder row);
}