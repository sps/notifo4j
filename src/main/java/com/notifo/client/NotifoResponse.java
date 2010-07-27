/**
 * Copyright (C) 2010 Sean P Scanlon.
 * All rights reserved. Unauthorized disclosure or distribution is prohibited.
 */
package com.notifo.client;

import com.google.gson.annotations.SerializedName;

/**
 * @author sscanlon
 * 
 */
public class NotifoResponse {

    private static final String SUCCESS = "success";

    private String status;

    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("response_message")
    private String responseMessage;

    public boolean isOk() {
        return SUCCESS.equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("status: ").append(status)
                .append(", responseCode: ").append(responseCode)
                .append(", responseMessage: ").append(responseMessage)
                .toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
