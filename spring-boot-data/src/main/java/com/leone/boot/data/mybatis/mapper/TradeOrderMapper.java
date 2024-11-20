package com.leone.boot.data.mybatis.mapper;

import com.leone.boot.data.mybatis.entity.TradeOrder;
import com.leone.boot.data.mybatis.response.OrderDetailResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TradeOrderMapper {
    
    int deleteByPrimaryKey(Long id);

    int insert(TradeOrder row);

    int insertSelective(TradeOrder row);

    TradeOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrder row);

    int updateByPrimaryKey(TradeOrder row);

    List<TradeOrder> selectByLimit(@Param("start") int start, @Param("size") int size);

    int count();

    List<TradeOrder> selectByRowBound(RowBounds rowBounds);

    List<TradeOrder> selectList();

    List<OrderDetailResp> selectOrderDetail();

}