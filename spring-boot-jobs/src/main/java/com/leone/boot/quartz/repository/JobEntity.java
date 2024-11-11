package com.leone.boot.quartz.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> quartz 实体
 *
 * @author leone
 * @since 2018-09-06
 **/
public class JobEntity implements Serializable {

    private Integer jobId;

    private String cron;

    private Integer status;

    private String jobName;

    private String jobGroup;

    private Date createTime;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
