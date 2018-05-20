package com.andy.pay.common.model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * 产品订单信息
 */
@Data
@ApiModel("订单模型")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;			// 订单ID
	private String buyerName;		// 卖家姓名
	private String buyerPhone;		// 卖家电话
	private String addressId;		// 卖家地址
	private Integer totalFee;		// 总金额(单位是分)
	private String createIp;		// 发起人IP地址
	private Integer status;			// 订单状态
	private Short payType;			// 支付类型(1:支付宝 2:微信 3:银联)
	private Short payWay;			// 支付方式1:手机2:电脑3:平板
	private String tradeNum;		// 订单号
	private String description;		// 订单描述
	private String createTime;		// 创建时间
	private String updateTime;		// 修改时间

}
