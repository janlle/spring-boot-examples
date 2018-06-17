package com.andy.pay.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-17 12:43
 **/
@Slf4j
public class HttpUtils {

    public static final String XML = "text/xml", JSON = "application/json", FORM = "text/plain", PARAM = "PARAM", HTML = "text/html";

    // 请求器的配置
    private static RequestConfig requestConfig;
    // 连接超时时间，默认10秒
    private static int socketTimeout = 10000;
    // 传输超时时间，默认30秒
    private static int connectTimeout = 30000;

    private static PoolingHttpClientConnectionManager clientConnectionManager;
    private static CloseableHttpClient httpClient= null;
    private static RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
    private final static Object lock = new Object();
    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        clientConnectionManager =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        clientConnectionManager.setMaxTotal(50);
        clientConnectionManager.setDefaultMaxPerRoute(25);

        try {
            SSLContext sslcontext = org.apache.http.conn.ssl.SSLContexts.custom().build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 根据默认超时限制初始化requestConfig
            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static CloseableHttpClient getHttpClient(){
        if(httpClient == null){
            synchronized (lock){
                if(httpClient == null){
                    BasicCookieStore cookieStore = new BasicCookieStore();
                    BasicClientCookie cookie = new BasicClientCookie("sessionID", "######");
                    cookie.setDomain("api");
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                    httpClient =HttpClients.custom().setConnectionManager(clientConnectionManager).setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
                }
            }
        }
        return httpClient;
    }

    /**
     * 获取sslHttpClient
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:06
     * @params: [certPath, password]
     * @return: org.apache.http.impl.client.CloseableHttpClient
     **/
    public static CloseableHttpClient getHttpsClient(String certPath, String password) throws Exception {
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
//            httpPost.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @author: Mr.lyon
     * @createBy: 2018-06-17 14:24
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
//            httpPost.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPostMap(String url, Map<String, Object> params) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
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


    public static String sendGet(String url) {
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

    public static String sendGet(String url, Map<String, String> params) {
        String result = null;
        if (params != null && params.size() > 0) {

        }
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        String json = "{\"return_msg\": \"OK\",\"return_code\": \"SUCCESS\"}";
        Map<String, Object> map = new HashMap();
        map.put("return_msg", "OK");
        map.put("return_code", "SUCCESS");
        try {
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            InputStream inputStream = new FileInputStream("D:/a.keystore");
//            keyStore.load(inputStream, "123456".toCharArray());
//            inputStream.close();
//            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).build();
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
//            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//        /*  List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//            formParams.add(new BasicNameValuePair("username", "test"));
//            HttpPost post = new HttpPost("https://localhost:8443/servlet/servlet/LoginServlet");
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams);
//            post.setEntity(entity);
//            CloseableHttpResponse respones = httpClient.execute(post);
//            EntityUtils.toString(respones.getEntity());*/
//            HttpPost request = new HttpPost("https://localhost:8443/servlet/servlet/LoginServlet");
//            List<BasicNameValuePair> formParams = new ArrayList();
//            formParams.add(new BasicNameValuePair("username", "中国"));
//            HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
//            request.setEntity(entity);
//            HttpResponse response = httpClient.execute(request);
//            // 打印请求信息
//            System.out.println(request.getRequestLine());
//            // 打印响应信息
//            System.out.println(response.getStatusLine());
//            response.getEntity().writeTo(System.out);
            String xmlResult = sendPostXml("http://localhost:8888/http/post", xml);
            log.info("请求xml响应的结果为:{}", xmlResult);

            String jsonResult = sendPostJson("http://localhost:8888/http/post1", json);
            log.info("请求json响应的结果为:{}", jsonResult);

            String mapResult = sendPostMap("http://localhost:8888/http/post2", map);
            log.info("请求map响应的结果为:{}", mapResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
