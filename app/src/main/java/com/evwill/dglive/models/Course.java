package com.evwill.dglive.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Course implements Parcelable {

    public Course() {}

    private String mName;
    private List<Hole> mHoles;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Hole> getHoles() {
        return mHoles;
    }

    public void setHoles(List<Hole> holes) {
        mHoles = holes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeTypedList(mHoles);
    }

    public Course(Parcel in) {
        mName = in.readString();
        in.readTypedList(mHoles, Hole.CREATOR);
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }

    };
}
