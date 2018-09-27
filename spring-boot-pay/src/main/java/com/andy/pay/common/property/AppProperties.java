package com.andy.pay.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leone
 * @since 2018-07-31
 **/
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {


    private WxProperties wx = new WxProperties();

    private String authRedirectUrl;

    public WxProperties getWx() {
        return wx;
    }

    public void setWx(WxProperties wx) {
        this.wx = wx;
    }

    public String getAuthRedirectUrl() {
        return authRedirectUrl;
    }

    public void setAuthRedirectUrl(String authRedirectUrl) {
        this.authRedirectUrl = authRedirectUrl;
    }
}
