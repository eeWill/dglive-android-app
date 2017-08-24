package com.evwill.dglive.models;

import com.evwill.dglive.models.Hole;

public class Score {
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
}
