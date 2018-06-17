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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
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

    private static PoolingHttpClientConnectionManager clientConnectionManager=null;
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
                    cookie.setDomain("#####");
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                    httpClient =HttpClients.custom().setConnectionManager(clientConnectionManager).setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
                }
            }
        }
        return httpClient;
    }



    public static HttpEntity httpGet(String url, Map<String,Object> headers){
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest httpGet = new HttpGet(url);
        if(headers!=null&&!headers.isEmpty()){
            httpGet = setHeaders(headers, httpGet);
        }
        CloseableHttpResponse response = null;
        try{
            response =httpClient.execute((HttpGet)httpGet);
            HttpEntity entity = response.getEntity();
            return entity;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
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

    /**
     使用表单键值对传参
     */
    public static HttpEntity httpPost(String url,Map<String,Object> headers,List<NameValuePair> data){
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest request = new HttpPost(url);
        if(headers!=null&&!headers.isEmpty()){
            request = setHeaders(headers,request);
        }
        CloseableHttpResponse response = null;
        UrlEncodedFormEntity uefEntity;
        try {
            HttpPost httpPost = (HttpPost) request;
            uefEntity = new UrlEncodedFormEntity(data,"UTF-8");
            httpPost.setEntity(uefEntity);
            // httpPost.setEntity(new StringEntity(data, ContentType.create("application/json", "UTF-8")));
            response=httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return entity;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String sendPostXml(String url, String data) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(data, "UTF-8");
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


    public static String sendGet(String url, String data) {
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            response.getEntity().writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return "";
    }


    public static void main(String[] args) {
        String data = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
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
            String result = sendPostXml("http://localhost:8888/http/post", data);
            log.info("响应的结果为:{}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
