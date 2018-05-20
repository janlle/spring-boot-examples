package com.andy.pay.modules.weixinpay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 20:34
 **/
@Configuration
@ConfigurationProperties(prefix = "app.pay.wechat")
public class WeChatProperty {

    private String appId;

    private String appSecret;

    private String merchantId;

    private String notifyUrl;

    private String nonceStr;

    private String body;

    private String wxPrePayUrl;

    private String wxOrderQueryUrl;

    private String wxCloseOrderUrl;

    private String wxRefundUrl;

    private String wxRefundQueryUrl;

    private String wxDownloadBillUrl;

    private String wxAppLoginUrl;

    private String wxGetTokenUrl;

    private String wxSendServeUrl;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "").substring(24);
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWxPrePayUrl() {
        return wxPrePayUrl;
    }

    public void setWxPrePayUrl(String wxPrePayUrl) {
        this.wxPrePayUrl = wxPrePayUrl;
    }

    public String getWxOrderQueryUrl() {
        return wxOrderQueryUrl;
    }

    public void setWxOrderQueryUrl(String wxOrderQueryUrl) {
        this.wxOrderQueryUrl = wxOrderQueryUrl;
    }

    public String getWxCloseOrderUrl() {
        return wxCloseOrderUrl;
    }

    public void setWxCloseOrderUrl(String wxCloseOrderUrl) {
        this.wxCloseOrderUrl = wxCloseOrderUrl;
    }

    public String getWxRefundUrl() {
        return wxRefundUrl;
    }

    public void setWxRefundUrl(String wxRefundUrl) {
        this.wxRefundUrl = wxRefundUrl;
    }

    public String getWxRefundQueryUrl() {
        return wxRefundQueryUrl;
    }

    public void setWxRefundQueryUrl(String wxRefundQueryUrl) {
        this.wxRefundQueryUrl = wxRefundQueryUrl;
    }

    public String getWxDownloadBillUrl() {
        return wxDownloadBillUrl;
    }

    public void setWxDownloadBillUrl(String wxDownloadBillUrl) {
        this.wxDownloadBillUrl = wxDownloadBillUrl;
    }

    public String getWxAppLoginUrl() {
        return wxAppLoginUrl;
    }

    public void setWxAppLoginUrl(String wxAppLoginUrl) {
        this.wxAppLoginUrl = wxAppLoginUrl;
    }

    public String getWxGetTokenUrl() {
        return wxGetTokenUrl;
    }

    public void setWxGetTokenUrl(String wxGetTokenUrl) {
        this.wxGetTokenUrl = wxGetTokenUrl;
    }

    public String getWxSendServeUrl() {
        return wxSendServeUrl;
    }

    public void setWxSendServeUrl(String wxSendServeUrl) {
        this.wxSendServeUrl = wxSendServeUrl;
    }
}
