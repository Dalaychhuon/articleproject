package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Response {

    @Expose
    @SerializedName("Pagination")
    private Pagination Pagination;
    @Expose
    @SerializedName("DATA")
    private List<Article> Article;
    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @Expose
    @SerializedName("CODE")
    private String CODE;

    public Pagination getPagination() {
        return Pagination;
    }

    public void setPagination(Pagination pagination) {
        this.Pagination = pagination;
    }

    public List<Article> getArticle() {
        return Article;
    }

    public void setArticle(List<Article> article) {
        this.Article = article;
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
