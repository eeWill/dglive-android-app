package com.evwill.dglive.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {
    private int mScore;
    private Hole mHole;

    public Score(int score, Hole hole) {
        mScore = score;
        mHole = hole;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public Hole getHole() {
        return mHole;
    }

    public void setHole(Hole hole) {
        mHole = hole;
    }

    public void incrementScore() {
        mScore = mScore + 1;
    }

    public void decrementScore() {
        mScore = mScore - 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mScore);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dest.writeTypedObject(mHole, flags);
        }
    }

    public Score(Parcel in) {
        mScore = in.readInt();
        mHole = in.readTypedObject(Hole.CREATOR);
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }

    };
}
