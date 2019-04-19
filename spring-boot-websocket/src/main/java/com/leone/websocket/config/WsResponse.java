package com.leone.websocket.config;

/**
 * @author Leone
 * @since 2018-07-06
 **/
public class WsResponse {

    private String message;

    public WsResponse(String message){
        this.message = message;
    }

    public String getResponseMessage() {
        return message;
    }

    public void setResponseMessage(String message) {
        this.message = message;
    }

}
