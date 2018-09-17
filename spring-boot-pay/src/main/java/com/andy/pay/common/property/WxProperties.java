package com.andy.pay.common.property;

import lombok.Data;

/**
 * @author Leone
 * @since 2018-06-03
 **/
@Data
public class WxProperties {

    private WXUrlProperty url = new WXUrlProperty();

    private String app_id;

    private String mch_id;

    private String app_secret;

    private String app_key;

    private String api_key;

    private String trade_type;

    private String partner_key;

    private String notify_url;

    private String create_order;

    /*授权url*/
    private String authUrl;

    /*获取token的url*/
    private String tokenUrl;

    /*获取openid的url*/
    private String sessionKeyUrl;


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPartner_key() {
        return partner_key;
    }

    public void setPartner_key(String partner_key) {
        this.partner_key = partner_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getCreate_order() {
        return create_order;
    }

    public void setCreate_order(String create_order) {
        this.create_order = create_order;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getSessionKeyUrl() {
        return sessionKeyUrl;
    }

    public void setSessionKeyUrl(String sessionKeyUrl) {
        this.sessionKeyUrl = sessionKeyUrl;
    }

}
