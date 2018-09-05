package com.andy.mvc.utils.kd;

import com.andy.mvc.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliExpressService {

    private static String appCode = "4111a0608c6c403bad2d492953665b77";

    private static String url = "http://wuliu.market.alicloudapi.com/kdi?no=%s&type=%s";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println(findKd("3366041775146", "STO"));
    }

    public static TracesVO findKd(String no, String type) {
        url = String.format(url, no, type);
        String stateStr = "暂无物流信息";
        Map<String, String> headers = new HashMap();
        headers.put("Authorization", "APPCODE " + appCode);
        TracesVO trace = null;
        try {
            String result = HttpUtil.sendGetNone(url, headers);
            trace = objectMapper.readValue(result, TracesVO.class);
            if (trace.getStatus().equals("0")) {
                String status = trace.getResult().getDeliverystatus();
                switch (status) {
                    case "1":
                        stateStr = "在途中";
                        break;
                    case "2":
                        stateStr = "正在派件";
                        break;
                    case "3":
                        stateStr = "签收";
                        break;
                    case "4":
                        stateStr = "派送失败";
                        break;
                }
                KdVO kdVO = new KdVO();
                List<KdStatusInfo> list = trace.getResult().getList();
                kdVO.setTraces(list);

                if (list != null && list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Map<String, Object> map = beanToMap(list.get(i));
                    }
                }
                kdVO.setLogisticCode(type);
                kdVO.setShipper(type);
                kdVO.setState(status);
                kdVO.setShipperCode(no);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trace;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

}
