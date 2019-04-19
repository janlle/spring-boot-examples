package com.leone.log.app.entity;

import java.io.Serializable;

/**
 * AppBaseLog
 */
public class AppBaseLog implements Serializable {

    // 日志创建时间
    private Long createdAtMs;

    // 应用唯一标识
    private String appId;

    // 租户唯一标识,企业用户
    private String tenantId;

    // 设备唯一标识
    private String deviceId;

    // 版本
    private String appVersion;

    // 渠道,安装时就在清单中制定了，appStore等。
    private String appChannel;

    // 平台
    private String appPlatform;

    // 操作系统
    private String osType;

    // 机型
    private String deviceStyle;

    public AppBaseLog() {
    }

    public AppBaseLog(Long createdAtMs, String appId, String tenantId, String deviceId, String appVersion, String appChannel, String appPlatform, String osType, String deviceStyle) {
        this.createdAtMs = createdAtMs;
        this.appId = appId;
        this.tenantId = tenantId;
        this.deviceId = deviceId;
        this.appVersion = appVersion;
        this.appChannel = appChannel;
        this.appPlatform = appPlatform;
        this.osType = osType;
        this.deviceStyle = deviceStyle;
    }

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceStyle() {
        return deviceStyle;
    }

    public void setDeviceStyle(String deviceStyle) {
        this.deviceStyle = deviceStyle;
    }
}
