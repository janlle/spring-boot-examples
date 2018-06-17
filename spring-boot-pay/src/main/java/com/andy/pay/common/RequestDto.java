package com.andy.pay.common;

import lombok.Data;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-17 14:27
 **/
@Data
public class RequestDto {

    private String return_msg;

    private String return_code;

    public RequestDto() {

    }
    public RequestDto(String return_msg, String return_code) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

}
