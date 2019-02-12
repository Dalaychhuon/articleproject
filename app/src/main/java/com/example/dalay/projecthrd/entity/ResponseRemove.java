package com.example.dalay.projecthrd.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRemove{
    @Expose
    @SerializedName("DATA")
    private Article Article;
    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @Expose
    @SerializedName("CODE")
    private String CODE;
    public ResponseRemove(){}


    public Article getArticle() {
        return Article;
    }

    public void setArticle(Article article) {
        Article = article;
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
