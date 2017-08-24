package com.evwill.dglive.models;

import java.util.List;

public class Player {
    private String mName;
    private List<Score> mScores;

    public List<Score> getScores() {
        return mScores;
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
}
