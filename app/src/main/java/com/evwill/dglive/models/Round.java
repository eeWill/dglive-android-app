package com.evwill.dglive.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Round implements Parcelable {
    private List<Player> mPlayers = new ArrayList<>();
    private Course mCourse;
    private int currentHoleNumber = 1;

    public Round() {}

    public int getCurrentHoleNumber() {
        return currentHoleNumber;
    }

    public void setCurrentHoleNumber(int currentHoleNumber) {
        this.currentHoleNumber = currentHoleNumber;
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(List<Player> players) {
        mPlayers = players;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public void decrementCurrentHoleNumber() {
        if(this.currentHoleNumber == 1) {
            return;
        }

        this.currentHoleNumber = this.currentHoleNumber - 1;
    }

    public void incrementCurrentHoleNumber() {

        if(this.currentHoleNumber == courseLength()) {
            return;
        }

        this.currentHoleNumber = this.currentHoleNumber + 1;
    }

    private int courseLength() {
        return getCourse().getHoles().size();
    }

    public void addPlayer(Player player) {
        mPlayers.add(player);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public Round(Parcel in) {
        in.readTypedList(this.getPlayers(), Player.CREATOR);
        in.readTypedObject(Course.CREATOR);
    }

    public static final Creator<Round> CREATOR = new Creator<Round>() {
        @Override
        public Round createFromParcel(Parcel source) {
            return new Round(source);
        }

        @Override
        public Round[] newArray(int size) {
            return new Round[size];
        }

    };
}
