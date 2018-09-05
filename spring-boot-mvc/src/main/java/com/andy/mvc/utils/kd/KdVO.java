package com.andy.mvc.utils.kd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-05
 **/
@Data
public class KdVO {

    @ApiModelProperty(value = "快递单号")
    private String logisticCode;

    @ApiModelProperty(value = "快递编号")
    private String shipperCode;

    @ApiModelProperty(value = "快递公司")
    private String shipper;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "详情列表")
    private List<KdStatusInfo> traces;

}
