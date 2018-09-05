package com.andy.mvc.utils;

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
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-05
 **/
@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    private final static String UTF8 = StandardCharsets.UTF_8.displayName();

    public static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(4000).setExpectContinueEnabled(true).build();
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(300);                  // 连接池最大并发连接数
        pool.setDefaultMaxPerRoute(50);         // 单路由最大并发数
        HttpRequestRetryHandler retryHandler = (IOException exception, int executionCount, HttpContext context) -> {
            if (executionCount > 1) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                log.info("[NoHttpResponseException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            } else if (exception instanceof SocketException) {
                log.info("[SocketException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            }
            return false;
        };
        httpClient = HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler).build();
    }

    /**
     * @param certPath
     * @param password
     * @return
     * @throws Exception
     */
    public static CloseableHttpClient sslHttpsClient(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream(new File(certPath))) {
            keyStore.load(inputStream, password.toCharArray());
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        return httpClient;
    }


    /**
     * 设置请求头信息
     *
     * @param headers
     * @param request
     * @return
     */
    private static HttpRequest setHeaders(Map<String, Object> headers, HttpRequest request) {
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
            StringEntity entity = new StringEntity(xml, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送json请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String sendPostJson(String url, String json) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(json, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送post
     *
     * @param url
     * @param params
     * @return
     */
    public static String sendPostMap(String url, Map<String, String> params) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                list.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String sendGetNone(String url, Map<String, String> header) {
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (null != header && header.size() > 0) {
                for (Map.Entry<String, String> entity : header.entrySet()) {
                    httpGet.setHeader(entity.getKey(), entity.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendGetMap(String url, Map<String, String> params) {
        String result = null;
        List<NameValuePair> pairs = new ArrayList();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet httpGet = new HttpGet(builder.build());
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {

    }

}
