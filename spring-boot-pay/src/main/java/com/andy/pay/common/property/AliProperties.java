package com.andy.pay.common.property;

import lombok.Data;

/**
 * @author Leone
 * @since 2018-06-03 15:33
 **/
@Data
public class AliProperties {

    //应用id
    public String APP_ID = "";

    //应用私钥
    public String ALIPAY_PRIVATE_KEY = "";

    //支付宝公钥
    public String ALIPAY_PUBLIC_KEY = "";

    //字符编码
    public String CHARSET = "UTF-8";

    //签名方式
    public String SIGN_TYPE = "RSA2";

    //数据格式
    public String LAYOUT = "JSON";

}
