package com.andy.log.app.entity;

/**
 * <p> 应用上报的使用时长相关信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class AppUsageLog extends AppBaseLog {

    private static final long serialVersionUID = 1L;

    // 单次使用时长(秒数),指一次启动内应用在前台的持续时长
    private Long singleUseDurationSecs;

    // 单次使用过程中的上传流量
    private Long singleUploadTraffic;

    // 单次使用过程中的下载流量
    private Long singleDownloadTraffic;

    public AppUsageLog() {
    }

    public AppUsageLog(Long singleUseDurationSecs, Long singleUploadTraffic, Long singleDownloadTraffic) {
        this.singleUseDurationSecs = singleUseDurationSecs;
        this.singleUploadTraffic = singleUploadTraffic;
        this.singleDownloadTraffic = singleDownloadTraffic;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getSingleUseDurationSecs() {
        return singleUseDurationSecs;
    }

    public void setSingleUseDurationSecs(Long singleUseDurationSecs) {
        this.singleUseDurationSecs = singleUseDurationSecs;
    }

    public Long getSingleUploadTraffic() {
        return singleUploadTraffic;
    }

    public void setSingleUploadTraffic(Long singleUploadTraffic) {
        this.singleUploadTraffic = singleUploadTraffic;
    }

    public Long getSingleDownloadTraffic() {
        return singleDownloadTraffic;
    }

    public void setSingleDownloadTraffic(Long singleDownloadTraffic) {
        this.singleDownloadTraffic = singleDownloadTraffic;
    }
}
