package com.leone.boot.log.app.entity;

import java.util.Map;

/**
 * <p>应用上报的事件相关信息
 *
 * @author leone
 * @since 2019-03-20
 **/
public class AppEventLog extends AppBaseLog {

    private static final long serialVersionUID = 1L;

    // 事件唯一标识
    private String eventId;

    // 事件持续时长
    private Integer eventDurationTime;

    // 参数名 键值对
    private Map<String, String> params;

    public AppEventLog() {
    }

    public AppEventLog(String eventId, Integer eventDurationTime, Map<String, String> params) {
        this.eventId = eventId;
        this.eventDurationTime = eventDurationTime;
        this.params = params;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getEventDurationTime() {
        return eventDurationTime;
    }

    public void setEventDurationTime(Integer eventDurationTime) {
        this.eventDurationTime = eventDurationTime;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
