package com.leone.mvc.utils.kd;

import com.leone.mvc.utils.kd.dto.KdStatus;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-05
 **/
public class KdVO {

    /*
     * 快递单号
     */
    private String shipperCode;

    /*
     *快递公司
     */
    private String shipper;

    /*
     *收货状态状态
     */
    private String status;

    /*
     *详情列表
     */
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
