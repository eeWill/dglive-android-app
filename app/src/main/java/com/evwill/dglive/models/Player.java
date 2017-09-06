package com.evwill.dglive.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Player implements Parcelable {
    private String mName;
    private List<Score> mScores;

    public Player(String name, List<Score> scores) {
        mScores = scores;
        mName = name;
    }

    public void setScores(List<Score> scores) {
        mScores = scores;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setScoreByHoleNumber(int holeNumber, Score score) {
        mScores.set(holeNumber, score);
    }

    public List<Score> getScores() {
        return mScores;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeList(mScores);
    }

    public Player(Parcel in) {
        List<Score> scores = new ArrayList<>();
        in.readString();
        in.readTypedList(scores, Score.CREATOR);
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }

    };
}
