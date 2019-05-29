package com.leone.boot.log.app.entity;

/**
 * <p> 应用上报的使用时长相关信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class AppUsageLog extends AppBaseLog {

    private static final long serialVersionUID = 1L;

    // 单次使用时长(秒数),指一次启动内应用在前台的持续时长
    private Integer singleUseDurationTime;

    // 单次使用过程中的上传流量
    private Long singleUploadFlow;

    // 单次使用过程中的下载流量
    private Long singleDownloadFlow;

    public AppUsageLog() {
    }

    public AppUsageLog(Integer singleUseDurationTime, Long singleUploadFlow, Long singleDownloadFlow) {
        this.singleUseDurationTime = singleUseDurationTime;
        this.singleUploadFlow = singleUploadFlow;
        this.singleDownloadFlow = singleDownloadFlow;
    }

    public Integer getSingleUseDurationTime() {
        return singleUseDurationTime;
    }

    public void setSingleUseDurationTime(Integer singleUseDurationTime) {
        this.singleUseDurationTime = singleUseDurationTime;
    }

    public Long getSingleUploadFlow() {
        return singleUploadFlow;
    }

    public void setSingleUploadFlow(Long singleUploadFlow) {
        this.singleUploadFlow = singleUploadFlow;
    }

    public Long getSingleDownloadFlow() {
        return singleDownloadFlow;
    }

    public void setSingleDownloadFlow(Long singleDownloadFlow) {
        this.singleDownloadFlow = singleDownloadFlow;
    }
}
