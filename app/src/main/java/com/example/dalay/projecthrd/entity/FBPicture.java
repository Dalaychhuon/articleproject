package com.example.dalay.projecthrd.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FBPicture implements Parcelable {
    private int height;
    private int width;
    private String url;
    private String is_silhouette;

    protected FBPicture(Parcel in) {
        height = in.readInt();
        width = in.readInt();
        url = in.readString();
        is_silhouette = in.readString();
    }

    public static final Creator<FBPicture> CREATOR = new Creator<FBPicture>() {
        @Override
        public FBPicture createFromParcel(Parcel in) {
            return new FBPicture(in);
        }

        @Override
        public FBPicture[] newArray(int size) {
            return new FBPicture[size];
        }
    };

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIs_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(String is_silhouette) {
        this.is_silhouette = is_silhouette;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(height);
        parcel.writeInt(width);
        parcel.writeString(url);
        parcel.writeString(is_silhouette);
    }
}
