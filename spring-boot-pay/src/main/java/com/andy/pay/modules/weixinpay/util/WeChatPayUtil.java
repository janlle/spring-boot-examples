package com.andy.pay.modules.weixinpay.util;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 13:17
 **/
@Slf4j
public class WeChatPayUtil {

    //获取一个随机的24位字符串
    public static String randomStr() {
        return UUID.randomUUID().toString().replace("-", "").substring(24);
    }

    public static void main(String[] args) {
        System.out.println(randomStr());
    }

}
