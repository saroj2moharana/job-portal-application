package com.skm.model;

/**
 * Created by  on 11/01/22
 **/
public class MessageResponse {
    String response;
    public MessageResponse(String s) {
        this.response = s;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
