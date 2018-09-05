package com.andy.design.behavior.duty;


/**
 * <p> 告警规则
 *
 * @author Leone
 * @since: 2018-08-22
 **/
public class AlarmRule {
    private String name;

    private String type;

    private String threshold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}