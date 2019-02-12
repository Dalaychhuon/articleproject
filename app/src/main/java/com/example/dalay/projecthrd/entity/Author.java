package com.example.dalay.projecthrd.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author implements Parcelable {

    @Expose
    @SerializedName("IMAGE_URL")
    private int IMAGE_URL;
    @Expose
    @SerializedName("FACEBOOK_ID")
    private int FACEBOOK_ID;
    @Expose
    @SerializedName("STATUS")
    private int STATUS;
    @Expose
    @SerializedName("TELEPHONE")
    private int TELEPHONE;
    @Expose
    @SerializedName("GENDER")
    private int GENDER;
    @Expose
    @SerializedName("EMAIL")
    private int EMAIL;
    @Expose
    @SerializedName("NAME")
    private int NAME;
    @Expose
    @SerializedName("ID")
    private int ID;

    protected Author(Parcel in) {
        IMAGE_URL = in.readInt();
        FACEBOOK_ID = in.readInt();
        STATUS = in.readInt();
        TELEPHONE = in.readInt();
        GENDER = in.readInt();
        EMAIL = in.readInt();
        NAME = in.readInt();
        ID = in.readInt();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public int getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(int IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public int getFACEBOOK_ID() {
        return FACEBOOK_ID;
    }

    public void setFACEBOOK_ID(int FACEBOOK_ID) {
        this.FACEBOOK_ID = FACEBOOK_ID;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(int TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public int getGENDER() {
        return GENDER;
    }

    public void setGENDER(int GENDER) {
        this.GENDER = GENDER;
    }

    public int getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(int EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getNAME() {
        return NAME;
    }

    public void setNAME(int NAME) {
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(IMAGE_URL);
        parcel.writeInt(FACEBOOK_ID);
        parcel.writeInt(STATUS);
        parcel.writeInt(TELEPHONE);
        parcel.writeInt(GENDER);
        parcel.writeInt(EMAIL);
        parcel.writeInt(NAME);
        parcel.writeInt(ID);
    }
}
