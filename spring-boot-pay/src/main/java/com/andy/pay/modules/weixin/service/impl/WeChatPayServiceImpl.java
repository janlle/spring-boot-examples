package com.andy.pay.modules.weixin.service.impl;

import com.andy.pay.common.model.Order;
import com.andy.pay.common.model.OrderMapper;
import com.andy.pay.common.model.OrderStatusEnum;
import com.andy.pay.modules.weixin.config.WeChatProperty;
import com.andy.pay.modules.weixin.service.WeChatPayService;
import com.andy.pay.modules.weixin.util.SignUtils;
import com.andy.pay.modules.weixin.util.WeChatPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static com.other.modules.weixinpay.util.XMLUtil.getChildrenText;

@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WeChatProperty weChatProperty;


    //微信预下单接口
    @Override
    public void payHandler(HttpServletRequest request, String orderId) {
        Order order = orderMapper.selectByOrderId(orderId);
        if (order == null) {
            log.error("微信预下单失败，订单不存在orderId:{}", orderId);
            return;
        } else if (order.getStatus() != OrderStatusEnum.CREATE.getStatus()) {
            log.error("微信预下单失败，订单状态不正确orderId:{}", orderId);
            return;
        }

        //微信支付是个必须要传入的参数
        Map<String, String> params = new HashMap<>();
        params.put("appid", weChatProperty.getAppId());             //appId
        params.put("mch_id", weChatProperty.getMchId());            //微信支付商户号
        params.put("nonce_str", WeChatPayUtil.randomStr());         //随机字符串
        params.put("body", "App weChat pay!");                      //商品描述
        params.put("out_trade_no", order.getTradeNum());            //商户订单号
        params.put("total_fee", order.getTotalFee().toString());    //总金额(分)
        params.put("spbill_create_ip", order.getCreateIp());        //订单生成的机器IP，指用户浏览器端IP
        params.put("notify_url", weChatProperty.getNotifyUrl());    //回调url
        params.put("trade_type", "APP");                            // 交易类型JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
        String sign = SignUtils.MD5Sign("UTF-8", params, weChatProperty.getApiKey());
        params.put("sign", sign);
        //参数xml化
        String xmlData = mapToXml(params);
        log.info("xmlData:{}", xmlData);
        //判断返回码
        String wxRetXmlData = sendPayReq(weChatProperty.getWeChatUrls().getPrePayUrl(), xmlData);
        log.info("微信返回数据:{}", wxRetXmlData);
        Map<String, String> retData = xmlToMap(wxRetXmlData);
        log.info("微信返回信息:{}", retData);
    }


    //向微信发起支付请求
    public String sendPayReq(String requestUrl, String xmlData) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputstream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            // 设置请求方式（GET/POST)
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            if (null != xmlData) {
                outputstream = connection.getOutputStream();
                outputstream.write(xmlData.getBytes("UTF-8"));
                outputstream.close();

            }
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String string = null;
            StringBuffer sb = new StringBuffer();
            while ((string = bufferedReader.readLine()) != null) {
                sb.append(string);
            }

            inputStream = null;

            return sb.toString();
        } catch (ConnectException ce) {
            log.info("http连接异常：{}" + ce);
        } catch (Exception e) {
            log.info("https请求异常：{}" + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                log.error("关闭资源异常:{}", e.getMessage());
            }
        }
        return null;

    }


    public static String mapToXml(Map<String, String> map){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            sb.append("<"+k+">"+v+"</"+k+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }


    public static Map<String, String> xmlToMap(String xmlData){
        if(null == xmlData || "".equals(xmlData)) {
            return null;
        }
        xmlData = xmlData.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        Map<String,String> map = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(inputStream);
            Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                String v = "";
                List children = e.getChildren();
                if(children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }
                map.put(k, v);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }


}
