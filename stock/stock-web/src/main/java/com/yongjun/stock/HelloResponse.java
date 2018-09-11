package com.yongjun.stock;

/**
 * Created by lyl on 2017/6/4.
 */
public class HelloResponse {

    private String responseMessage;

    public HelloResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
