package com.evwill.dglive.models;

import java.util.List;

public class Player {
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
}
