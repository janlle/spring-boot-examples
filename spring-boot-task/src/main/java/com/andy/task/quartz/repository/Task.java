package com.andy.task.quartz.repository;

import javax.persistence.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-06
 **/
@Entity
public class Task extends IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Column
    private String cron;


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
