package com.andy.pay.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * 工程中常用工具
 * @author: Mr.ruoLin
 * @createBy: 2018-05-10
 **/
@Slf4j
public class AppUtils {

    public static String urlEncoder(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecoder(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成md5签名的方法
     * @author: Mr.lyon
     * @createBy: 2018/6/3 14:59
     * @params: [charset, params, apiKey]
     * @return: java.lang.String
     **/
    public static String createSign(String charset, Map params, String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        return MD5Encode(sb.toString(), charset).toUpperCase();
    }

    /**
     * 生成MD5摘要算法
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:00
     * @params: [message, charset]
     * @return: java.lang.String
     **/
    public static String MD5Encode(String message, String charset) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] buff;
            if (charset == null || "".equals(charset)) {
                buff = messageDigest.digest(message.getBytes());
            } else {
                buff = messageDigest.digest(message.getBytes(charset));
            }
            StringBuffer sb = new StringBuffer();
            int digital;
            for (int i = 0; i < buff.length; i++) {
                digital = buff[i];
                if(digital < 0) {
                    digital += 256;
                }
                if(digital < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(digital));
            }
            result = sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成 HMAC_SHA256
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:01
     * @params: [data, key]
     * @return: java.lang.String
     **/
    public static String HMAC_SHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }


    /**
     * XML格式字符串转换为Map
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:02
     * @params: [xmlStr]
     * @return: java.util.Map
     **/
    public static Map xmlToMap(String xmlStr)  {
        try (InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"))) {
            Map<String, String> data = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            return data;
        } catch (Exception ex) {
            log.warn("xml转换map异常,message: {}. XML content: {}", ex.getMessage(), xmlStr);
        }
        return null;
    }


    /**
     * map转换为xml字符串
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:02
     * @params: [params]
     * @return: java.lang.String
     **/
    public static String mapToXml(Map params) {
        StringBuilder sb = new StringBuilder();
        Set set = params.entrySet();
        Iterator it = set.iterator();
        sb.append("<xml>\n");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            sb.append("<" + k + ">").append(v).append("</" + k + ">\n");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 生成32位随机数字
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:02
     * @params: []
     * @return: java.lang.String
     **/
    public static String genNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前时间戳，单位秒(10位)
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:02
     * @params: []
     * @return: java.lang.String
     **/
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 生成32位字符串
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:02
     * @params: []
     * @return: java.lang.String
     **/
    public static String generateNum(int length) {
        StringBuffer result = new StringBuffer();
        final String sources = "0123456789";
        if (length < 0 && length > 512) {
            return null;
        }
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            result.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(genNonceStr());
        System.out.println(getTimestamp());
        System.out.println(generateNum(6));
        System.out.println(MD5Encode("hello", "UTF-8"));
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (log.isInfoEnabled()) {
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }


}
