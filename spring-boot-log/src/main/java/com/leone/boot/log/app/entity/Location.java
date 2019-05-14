package com.leone.boot.log.app.entity;

/**
 * <p>地理信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class Location {

    // 国家
    private String country;

    // 省份
    private String province;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
