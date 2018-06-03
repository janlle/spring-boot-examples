package com.andy.pay.modules.weixin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.security.KeyStore;
import java.util.*;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 13:17
 **/
@Slf4j
public class WeChatPayUtil {

    //获取一个随机的24位字符串
    public static String randomStr() {
        return UUID.randomUUID().toString().replace("-", "").substring(24);
    }

    public static String PostRequest(String url, String data) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post=new PostMethod(url);
        String result = "";
        post.addRequestHeader("Content-Type", "text/html; charset=utf-8");
        post.addRequestHeader("content", "text/html; charset=utf-8");
        post.setRequestBody(data);
        try {
            int status=client.executeMethod(post);
            result = post.getResponseBodyAsString();
            result = new String(result.getBytes(post.getResponseCharSet()), "utf-8");
        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @Description:微信支付 统一下单
     * @param out_trade_no
     * @param body
     * @param detail
     * @param total_fee
     * @param ip_address
     * @Date: 2017-9-11 14:35
     * @return:
     */
    public static String unifiedOrder(String out_trade_no, String body, String detail, int total_fee,String ip_address) {
        StringBuffer xml = new StringBuffer();
        String data = null;
        try{
            xml.append("</xml>");
            if (body.length() > 32) {
                body = body.substring(0, 32);
            }
            SortedMap<String, String> parameters = new TreeMap();
            parameters.put("appid", WXPayConstants.APP_ID);
            parameters.put("body", body);
            parameters.put("detail", detail);
            parameters.put("mch_id", WXPayConstants.MCH_ID);
            parameters.put("nonce_str", genNonceStr());
            parameters.put("notify_url", "http://www.aidongsports.com/wx");
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("fee_type", "CNY");
            parameters.put("spbill_create_ip", ip_address);
            parameters.put("total_fee", String.valueOf(total_fee));
            parameters.put("trade_type", "APP");
            parameters.put("sign", WeChatUtil.createSign(parameters, WXPayConstants.API_KEY));
            data = PostRequest("https://api.mch.weixin.qq.com/pay/unifiedorder",SortedMaptoXml(parameters));
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
    /**
     * @Description:微信退款
     * @param out_trade_no
     * @param total_fee
     * @return:
     */
    public static String wxPayRefund(String out_trade_no, String transaction_id,String total_fee) {
        StringBuffer xml = new StringBuffer();
        String data = null;
        try {
            String nonceStr = genNonceStr();
            xml.append("</xml>");
            SortedMap<String,String> parameters = new TreeMap<String,String>();
            parameters.put("appid", WXPayConstants.APP_ID);
            parameters.put("mch_id", WXPayConstants.MCH_ID);
            parameters.put("nonce_str", nonceStr);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("transaction_id", transaction_id);
            parameters.put("out_refund_no", nonceStr);
            parameters.put("fee_type", "CNY");
            parameters.put("total_fee", total_fee);
            parameters.put("refund_fee", total_fee);
            parameters.put("op_user_id", WXPayConstants.MCH_ID);
            parameters.put("sign", WeChatUtil.createSign(parameters, WXPayConstants.API_KEY));
            data =SortedMaptoXml(parameters);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return data;
    }
    /**
     * 证书使用
     * 微信退款
     */
    public static String wxPayBack(String url, String data) throws Exception {
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream inputStream = new FileInputStream(new File("D:\\微信商户平台支付证书\\apiclient_cert.p12"));
        String result="";
        try {
            keyStore.load(inputStream, WXPayConstants.MCH_ID.toCharArray());
        } finally {
            inputStream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, WXPayConstants.MCH_ID.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            StringEntity entitys = new StringEntity(data);
            httppost.setEntity((HttpEntity) entitys);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text="";
                    String t="";
                    while ((text=bufferedReader.readLine()) != null) {
                        t+=text;
                    }
                    byte[] temp=t.getBytes("gbk");//这里写原编码方式
                    String newStr=new String(temp,"utf-8");//这里写转换后的编码方式
                    result=newStr;
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }

    /**
     * XML格式字符串转换为Map
     * 微信支付 解析xml xml转map  获取prepay_id
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            log.warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

    /**
     * 获取随机字符串 Nonce Str
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }



    /**
     * @Description:通过prepay_id 生成微信支付参数
     * @param prepay_id
     * @Date: 2017-9-8 10:17
     */
    public static  SortedMap<Object,Object> genPayRequest(String prepay_id) {
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", WXPayConstants.APP_ID);
        parameters.put("noncestr", genNonceStr());
        parameters.put("package", "Sign=WXPay");
        parameters.put("partnerid", WXPayConstants.MCH_ID);
        parameters.put("prepayid", prepay_id);
        parameters.put("timestamp",getCurrentTimestamp());
        parameters.put("sign", WeChatUtil.createSign(parameters).toUpperCase());
        return parameters;
    }
    /**
     * @Author: HONGLINCHEN
     * @Description:请求值转换为xml格式 SortedMap转xml
     * @param params
     * @Date: 2017-9-7 17:18
     */
    private static String SortedMaptoXml(SortedMap<String,String> params) {
        StringBuilder sb = new StringBuilder();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        sb.append("<xml>\n");
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            sb.append("<"+k+">");
            sb.append(v);
            sb.append("</"+k+">\n");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 生成32位随机数字
     */
//    public static String genNonceStr() {
//        Random random = new Random();
//        return WeChatUtil.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
//    }
    /**
     * 获取当前时间戳，单位秒
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }


}
