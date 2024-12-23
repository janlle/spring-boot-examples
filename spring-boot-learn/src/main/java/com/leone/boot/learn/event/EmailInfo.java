package com.leone.boot.learn.event;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-04
 **/
public class EmailInfo {

    private String address;

    private String text;

    public EmailInfo() {
    }

    public EmailInfo(String address, String text) {
        this.address = address;
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
