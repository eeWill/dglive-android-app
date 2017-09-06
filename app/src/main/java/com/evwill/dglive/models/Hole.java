package com.evwill.dglive.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hole implements Parcelable {
    private int mPar;
    private int mLength;
    private String mName;
    private int mOrder;

    public Hole(String name, int par) {
        mName = name;
        mPar = par;
    }

    public int getPar() {
        return mPar;
    }

    public void setPar(int par) {
        mPar = par;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mPar);
        dest.writeInt(mLength);
        dest.writeString(mName);
        dest.writeInt(mOrder);
    }

    public Hole(Parcel in) {
        mPar = in.readInt();
        mLength = in.readInt();
        mName = in.readString();
        mOrder = in.readInt();
    }

    public static final Creator<Hole> CREATOR = new Creator<Hole>() {
        @Override
        public Hole createFromParcel(Parcel source) {
            return new Hole(source);
        }

        @Override
        public Hole[] newArray(int size) {
            return new Hole[size];
        }

    };
}
