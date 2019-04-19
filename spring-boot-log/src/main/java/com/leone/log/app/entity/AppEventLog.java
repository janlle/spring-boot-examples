package com.leone.log.app.entity;

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
    private Long eventDurationSecs;

    // 参数名 键值对
    private Map<String, String> paramKeyValueMap;


    public AppEventLog(String eventId, Long eventDurationSecs, Map<String, String> paramKeyValueMap) {
        this.eventId = eventId;
        this.eventDurationSecs = eventDurationSecs;
        this.paramKeyValueMap = paramKeyValueMap;
    }

    public AppEventLog() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getEventDurationSecs() {
        return eventDurationSecs;
    }

    public void setEventDurationSecs(Long eventDurationSecs) {
        this.eventDurationSecs = eventDurationSecs;
    }

    public Map<String, String> getParamKeyValueMap() {
        return paramKeyValueMap;
    }

    public void setParamKeyValueMap(Map<String, String> paramKeyValueMap) {
        this.paramKeyValueMap = paramKeyValueMap;
    }
}
