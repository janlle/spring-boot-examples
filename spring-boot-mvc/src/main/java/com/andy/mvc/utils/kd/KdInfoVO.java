package com.andy.mvc.utils.kd;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-05
 **/
@Data
public class KdInfoVO {

    private String number;

    private String type;

    private String deliverystatus;

    private String issign;

    private String expName;

    private String expSite;

    private String expPhone;

    private List<KdStatusInfo> list;

}
