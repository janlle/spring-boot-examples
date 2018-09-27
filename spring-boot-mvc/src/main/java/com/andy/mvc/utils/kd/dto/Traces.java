package com.andy.mvc.utils.kd.dto;

import lombok.Data;

/**
 * <p> 用于快递状态
 * "time": "2018-03-09 11:59:26",
 * "status": "【石家庄市】 快件已在 【长安三部】 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"
 */
@Data
public class Traces {

    private String status;

    private String msg;

    private KdDetail result;

}

