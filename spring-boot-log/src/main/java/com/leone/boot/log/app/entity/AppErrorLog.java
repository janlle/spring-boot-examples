package com.leone.boot.log.app.entity;

/**
 * <p>应用上报的app错误日志相关信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class AppErrorLog extends AppBaseLog {

    private static final long serialVersionUID = 1L;

    // 错误摘要
    private String errorBrief;

    // 错误详情
    private String errorDetail;

    public AppErrorLog() {

    }

    public AppErrorLog(Long createdAtMs, String appId, String tenantId, String deviceId, String appVersion, String appChannel, String appPlatform, String osType, String deviceStyle, String errorBrief, String errorDetail) {
        super(createdAtMs, appId, tenantId, deviceId, appVersion, appChannel, appPlatform, osType, deviceStyle);
        this.errorBrief = errorBrief;
        this.errorDetail = errorDetail;
    }

    public String getErrorBrief() {
        return errorBrief;
    }

    public void setErrorBrief(String errorBrief) {
        this.errorBrief = errorBrief;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return "AppErrorLog{" +
                "errorBrief='" + errorBrief + '\'' +
                ", errorDetail='" + errorDetail + '\'' +
                '}';
    }
}