package com.andy.pay.common.property;

import lombok.Data;

/**
 * @author: Leone
 * @since: 2018-06-03 15:33
 **/
@Data
public class AliProperty {

    private AliUrlProperty url = new AliUrlProperty();

    //应用id
    public static final String APP_ID = "";

    //应用私钥
    public static final String ALIPAY_PRIVATE_KEY = "";

    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "";

    //字符编码
    public static final String CHARSET = "UTF-8";

    //签名方式
    public static final String SIGN_TYPE = "RSA2";

    //数据格式
    public static final String LAYOUT = "JSON";

}
