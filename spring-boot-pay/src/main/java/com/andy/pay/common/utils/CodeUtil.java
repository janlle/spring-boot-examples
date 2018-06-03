package com.andy.pay.common.utils;

import java.util.UUID;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-9
 **/
public class CodeUtil {

    /**
     * 生成32位随机字符串
     * @return String
     */
    public static String gen32Code(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成随机六位数字
     * @return String
     */
    public static String genSexNum(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }


    /**
     * 生成随机十二位数字
     * @return String
     */
    public static String gen12Code(){
        return UUID.randomUUID().toString().replace("-", "").substring(3, 15);
    }

}
