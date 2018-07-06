package com.andy.websocket.config;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-06 22:10
 **/
public class WiselyResponse {

    private String responseMessage;

    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
