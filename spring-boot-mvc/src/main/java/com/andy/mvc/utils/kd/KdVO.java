package com.andy.mvc.utils.kd;

import com.luwei.services.kuaidi.kd.KdStatus;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-05
 **/
public class KdVO {

    @ApiModelProperty("快递单号")
    private String shipperCode;

    @ApiModelProperty("快递公司")
    private String shipper;

    @ApiModelProperty("收货状态状态")
    private String status;

    @ApiModelProperty("详情列表")
    private List<KdStatus> detail;

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<KdStatus> getDetail() {
        return detail;
    }

    public void setDetail(List<KdStatus> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "KdVO{" +
                "shipperCode='" + shipperCode + '\'' +
                ", shipper='" + shipper + '\'' +
                ", status='" + status + '\'' +
                ", detail=" + detail +
                '}';
    }
}
