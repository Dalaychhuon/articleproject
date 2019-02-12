package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class dalay {

    @Expose
    @SerializedName("DATA")
    private DATAEntity DATA;
    @Expose
    @SerializedName("MESSAGE")
    private String MESSAGE;
    @Expose
    @SerializedName("CODE")
    private String CODE;

    public DATAEntity getDATA() {
        return DATA;
    }

    public void setDATA(DATAEntity DATA) {
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

    public static class DATAEntity {
        @Expose
        @SerializedName("IMAGE")
        private String IMAGE;
        @Expose
        @SerializedName("CATEGORY")
        private CATEGORYEntity CATEGORY;
        @Expose
        @SerializedName("STATUS")
        private String STATUS;
        @Expose
        @SerializedName("AUTHOR")
        private AUTHOREntity AUTHOR;
        @Expose
        @SerializedName("CREATED_DATE")
        private String CREATED_DATE;
        @Expose
        @SerializedName("DESCRIPTION")
        private String DESCRIPTION;
        @Expose
        @SerializedName("TITLE")
        private String TITLE;
        @Expose
        @SerializedName("ID")
        private int ID;

        public String getIMAGE() {
            return IMAGE;
        }

        public void setIMAGE(String IMAGE) {
            this.IMAGE = IMAGE;
        }

        public CATEGORYEntity getCATEGORY() {
            return CATEGORY;
        }

        public void setCATEGORY(CATEGORYEntity CATEGORY) {
            this.CATEGORY = CATEGORY;
        }

        public String getSTATUS() {
            return STATUS;
        }

        public void setSTATUS(String STATUS) {
            this.STATUS = STATUS;
        }

        public AUTHOREntity getAUTHOR() {
            return AUTHOR;
        }

        public void setAUTHOR(AUTHOREntity AUTHOR) {
            this.AUTHOR = AUTHOR;
        }

        public String getCREATED_DATE() {
            return CREATED_DATE;
        }

        public void setCREATED_DATE(String CREATED_DATE) {
            this.CREATED_DATE = CREATED_DATE;
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

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }

    public static class CATEGORYEntity {
        @Expose
        @SerializedName("NAME")
        private String NAME;
        @Expose
        @SerializedName("ID")
        private int ID;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }

    public static class AUTHOREntity {
        @Expose
        @SerializedName("IMAGE_URL")
        private String IMAGE_URL;
        @Expose
        @SerializedName("FACEBOOK_ID")
        private String FACEBOOK_ID;
        @Expose
        @SerializedName("STATUS")
        private String STATUS;
        @Expose
        @SerializedName("TELEPHONE")
        private String TELEPHONE;
        @Expose
        @SerializedName("GENDER")
        private String GENDER;
        @Expose
        @SerializedName("EMAIL")
        private String EMAIL;
        @Expose
        @SerializedName("NAME")
        private String NAME;
        @Expose
        @SerializedName("ID")
        private int ID;

        public String getIMAGE_URL() {
            return IMAGE_URL;
        }

        public void setIMAGE_URL(String IMAGE_URL) {
            this.IMAGE_URL = IMAGE_URL;
        }

        public String getFACEBOOK_ID() {
            return FACEBOOK_ID;
        }

        public void setFACEBOOK_ID(String FACEBOOK_ID) {
            this.FACEBOOK_ID = FACEBOOK_ID;
        }

        public String getSTATUS() {
            return STATUS;
        }

        public void setSTATUS(String STATUS) {
            this.STATUS = STATUS;
        }

        public String getTELEPHONE() {
            return TELEPHONE;
        }

        public void setTELEPHONE(String TELEPHONE) {
            this.TELEPHONE = TELEPHONE;
        }

        public String getGENDER() {
            return GENDER;
        }

        public void setGENDER(String GENDER) {
            this.GENDER = GENDER;
        }

        public String getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(String EMAIL) {
            this.EMAIL = EMAIL;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }
}
