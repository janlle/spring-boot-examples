package com.andy.pay.object.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("")
public class Site {

    private Integer id;

    private String siteId;

    private Integer parentId;

    private String name;

    private String shortName;

    private Float longitude;

    private Float latitude;

    private Integer level;

    private Integer sort;

    private String status;


}