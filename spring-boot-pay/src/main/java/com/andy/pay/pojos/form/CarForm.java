package com.andy.pay.pojos.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Leone
 * @since: 2018-06-03 20:12
 **/
@Data
@ApiModel("购物车表单模型")
public class CarForm {

    private String productId;

    private Integer productCount;

}
