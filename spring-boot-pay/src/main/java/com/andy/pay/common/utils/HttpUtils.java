package com.andy.pay.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author Leone
 * @since: 2018-06-17 12:43
 **/
@Slf4j
public class HttpUtils {

    private final static String UTF8 = "UTF-8";

    public static final String XML = "text/xml", JSON = "application/json", FORM = "text/plain", PARAM = "PARAM", HTML = "text/html";

    public static CloseableHttpClient httpClient;

    // 初始化线程池
    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(4000).setExpectContinueEnabled(true).build();
        PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager();
        pccm.setMaxTotal(300);                  // 连接池最大并发连接数
        pccm.setDefaultMaxPerRoute(50);         // 单路由最大并发数
        HttpRequestRetryHandler retryHandler = (IOException exception, int executionCount, HttpContext context) -> {
            // 重试1次,从1开始
            if (executionCount > 1) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                log.info("[NoHttpResponseException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            }
            else if (exception instanceof SocketException) {
                log.info("[SocketException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            }
            return false;
        };
        httpClient = HttpClients.custom().setConnectionManager(pccm).setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler).build();
    }

    /**
     * 获取sslHttpClient
     * @author Leone
     * @since: 2018/6/3 15:06
     * @params: [certPath, password]
     * @return: org.apache.http.impl.client.CloseableHttpClient
     **/
    public static CloseableHttpClient sslHttpsClient(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream(new File(certPath))) {
            keyStore.load(inputStream, password.toCharArray());
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        return httpClient;
    }


    /**
     * 设置请求头信息
     * @param headers
     * @param request
     * @return
     */
    private static HttpRequest setHeaders(Map<String,Object> headers, HttpRequest request) {
        for (Map.Entry entry : headers.entrySet()) {
            if (!entry.getKey().equals("Cookie")) {
                request.addHeader((String) entry.getKey(), (String) entry.getValue());
            } else {
                Map<String, Object> Cookies = (Map<String, Object>) entry.getValue();
                for (Map.Entry entry1 : Cookies.entrySet()) {
                    request.addHeader(new BasicHeader("Cookie", (String) entry1.getValue()));
                }
            }
        }
        return request;
    }

    public static String sendPostXml(String url, String xml) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(xml, "UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @author Leone
     * @since: 2018-06-17 14:24
     * @params: [url, json]
     * @return: java.lang.String
     **/
    public static String sendPostJson(String url, String json) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPostMap(String url, Map<String, String> params) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                list.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String sendGetNone(String url) {
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendGetMap(String url, Map<String, String> params) {
        String result = null;
        List<NameValuePair> pairs = new ArrayList();
        if (params != null && params.size() > 0) {
            for(Map.Entry<String,String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet httpGet = new HttpGet(builder.build());
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        String json = "{\"return_msg\": \"OK\",\"return_code\": \"SUCCESS\"}";
        Map<String, String> map = new HashMap();
        map.put("return_msg", "OK");
        map.put("return_code", "SUCCESS");
        try {
            String xmlResult = sendPostXml("http://localhost:8888/http/post", xml);
            log.info("post请求xml响应的结果为:{}", xmlResult);

            String jsonResult = sendPostJson("http://localhost:8888/http/post1", json);
            log.info("post请求json响应的结果为:{}", jsonResult);

            String mapResult = sendPostMap("http://localhost:8888/http/post2", map);
            log.info("post请求map响应的结果为:{}", mapResult);

            String getResult = sendGetMap("http://localhost:8888/http/get", map);
            log.info("get请求map响应的结果为:{}", getResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
