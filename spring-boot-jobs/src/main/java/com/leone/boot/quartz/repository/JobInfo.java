package com.leone.boot.quartz.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> quartz 实体
 *
 * @author leone
 * @since 2018-09-06
 **/
@Data
public class JobInfo implements Serializable {

    private Long id;

    private String jobId;

    private String jobName;

    private String jobGroup;

    private String jobClassName;

    private String jobCron;

    private String jobParam;

    private String jobDesc;

    private Integer jobStatus;

    // 任务创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;

    private Integer deleted;


}
