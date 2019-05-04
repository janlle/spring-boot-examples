package com.leone.mvc.utils.qiniu;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-02
 **/
public enum ZoneEnum {


    HUA_DONG("华东"),
    HUA_NAN("华南"),
    HUA_BEI("华北"),
    DONG_NAN_YA("东南亚"),
    BEI_MEI("北美");

    private String zone;

    ZoneEnum(String zone) {
        this.zone = zone;
    }

}
