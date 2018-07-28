package com.andy.websocket.config;

/**
 * @author: lyon
 * @createBy: 2018-07-06 22:10
 **/
public class WSResponse {

    private String message;

    public WSResponse(String message){
        this.message = message;
    }

    public String getResponseMessage() {
        return message;
    }

    public void setResponseMessage(String message) {
        this.message = message;
    }

}
