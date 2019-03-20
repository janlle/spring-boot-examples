package com.andy.log.app.entity;

/**
 * AppLog实体类
 * 内部含有各种日志时间的集合。
 */
public class AppLogEntity {

    // 应用唯一标识
    private String appId;

    // 租户唯一标识,企业用户
    private String tenantId;

    // 设备唯一标识
    private String deviceId;

    // 版本
    private String appVersion;

    // 渠道,安装时就在清单中制定了，appStore等
    private String appChannel;

    // 平台
    private String appPlatform;

    // 操作系统
    private String osType;

    // 机型
    private String deviceStyle;

    // 启动相关信息的数组
    private AppStartupLog[] appStartupLogs;

    // 页面跳转相关信息的数组
    private AppPageLog[] appPageLogs;

    // 事件相关信息的数组
    private AppEventLog[] appEventLogs;

    // app使用情况相关信息的数组
    private AppUsageLog[] appUsageLogs;

    // 错误相关信息的数组
    private AppErrorLog[] appErrorLogs;

    public AppLogEntity() {
    }

    public AppLogEntity(String appId, String tenantId, String deviceId, String appVersion, String appChannel, String appPlatform, String osType, String deviceStyle, AppStartupLog[] appStartupLogs, AppPageLog[] appPageLogs, AppEventLog[] appEventLogs, AppUsageLog[] appUsageLogs, AppErrorLog[] appErrorLogs) {
        this.appId = appId;
        this.tenantId = tenantId;
        this.deviceId = deviceId;
        this.appVersion = appVersion;
        this.appChannel = appChannel;
        this.appPlatform = appPlatform;
        this.osType = osType;
        this.deviceStyle = deviceStyle;
        this.appStartupLogs = appStartupLogs;
        this.appPageLogs = appPageLogs;
        this.appEventLogs = appEventLogs;
        this.appUsageLogs = appUsageLogs;
        this.appErrorLogs = appErrorLogs;
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

    public AppStartupLog[] getAppStartupLogs() {
        return appStartupLogs;
    }

    public void setAppStartupLogs(AppStartupLog[] appStartupLogs) {
        this.appStartupLogs = appStartupLogs;
    }

    public AppPageLog[] getAppPageLogs() {
        return appPageLogs;
    }

    public void setAppPageLogs(AppPageLog[] appPageLogs) {
        this.appPageLogs = appPageLogs;
    }

    public AppEventLog[] getAppEventLogs() {
        return appEventLogs;
    }

    public void setAppEventLogs(AppEventLog[] appEventLogs) {
        this.appEventLogs = appEventLogs;
    }

    public AppUsageLog[] getAppUsageLogs() {
        return appUsageLogs;
    }

    public void setAppUsageLogs(AppUsageLog[] appUsageLogs) {
        this.appUsageLogs = appUsageLogs;
    }

    public AppErrorLog[] getAppErrorLogs() {
        return appErrorLogs;
    }

    public void setAppErrorLogs(AppErrorLog[] appErrorLogs) {
        this.appErrorLogs = appErrorLogs;
    }
}
