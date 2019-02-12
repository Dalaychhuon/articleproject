package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ImageResponse {

    @Expose
    @SerializedName("DATA")
    private String DATA;
    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @Expose
    @SerializedName("CODE")
    private String CODE;

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }
}
