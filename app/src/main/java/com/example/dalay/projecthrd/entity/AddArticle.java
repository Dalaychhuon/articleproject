package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class AddArticle {

    @Expose
    @SerializedName("IMAGE")
    private String IMAGE;
    @Expose
    @SerializedName("STATUS")
    private String STATUS;
    @Expose
    @SerializedName("CATEGORY_ID")
    private int CATEGORY_ID;
    @Expose
    @SerializedName("AUTHOR")
    private int AUTHOR;
    @Expose
    @SerializedName("DESCRIPTION")
    private String DESCRIPTION;
    @Expose
    @SerializedName("TITLE")
    private String TITLE;

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public int getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(int CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public int getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(int AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
