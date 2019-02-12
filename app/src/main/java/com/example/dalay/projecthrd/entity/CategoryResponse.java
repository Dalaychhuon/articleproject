package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @Expose
    @SerializedName("PAGINATION")
    private Pagination Pagination;
    @Expose
    @SerializedName("DATA")
    private List<Category> categories;
    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @Expose
    @SerializedName("CODE")
    private String CODE;

    public CategoryResponse() {
    }

    public Pagination getPagination() {
        return Pagination;
    }

    public void setPagination(Pagination pagination) {
        Pagination = pagination;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
