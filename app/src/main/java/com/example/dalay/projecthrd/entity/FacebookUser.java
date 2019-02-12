package com.example.dalay.projecthrd.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FacebookUser implements Parcelable{
    private String id;
    private String first_name;
    private String last_name;
    private FbData picture;
    private String email;

    protected FacebookUser(Parcel in) {
        id = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        picture = in.readParcelable(FbData.class.getClassLoader());
        email = in.readString();
    }

    public static final Creator<FacebookUser> CREATOR = new Creator<FacebookUser>() {
        @Override
        public FacebookUser createFromParcel(Parcel in) {
            return new FacebookUser(in);
        }

        @Override
        public FacebookUser[] newArray(int size) {
            return new FacebookUser[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public FbData getPicture() {
        return picture;
    }

    public void setPicture(FbData picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeParcelable(picture, i);
        parcel.writeString(email);
    }
}
