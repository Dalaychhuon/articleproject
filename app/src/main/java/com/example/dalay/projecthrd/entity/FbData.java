package com.example.dalay.projecthrd.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FbData implements Parcelable {
    private FBPicture data;

    protected FbData(Parcel in) {
        data = in.readParcelable(FBPicture.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FbData> CREATOR = new Creator<FbData>() {
        @Override
        public FbData createFromParcel(Parcel in) {
            return new FbData(in);
        }

        @Override
        public FbData[] newArray(int size) {
            return new FbData[size];
        }
    };

    public FBPicture getData() {
        return data;
    }

    public void setData(FBPicture data) {
        this.data = data;
    }
}
