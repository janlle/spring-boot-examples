package com.andy.pay.common.model;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 19:46
 **/
public interface OrderMapper {

    @Select("select * from t_order_master where order_id = #{orderId}")
    Order selectByOrderId(@Param("orderId") String orderId);

}
