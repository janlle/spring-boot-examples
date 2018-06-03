package com.andy.pay.mapper;

import com.andy.pay.entity.Receiver;

public interface ReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Receiver record);

    int insertSelective(Receiver record);

    Receiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Receiver record);

    int updateByPrimaryKey(Receiver record);
}