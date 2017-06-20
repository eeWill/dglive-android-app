package com.evwill.dglive;

public class Round {
    private Player[] mPlayers;

    public Player[] getPlayers() {
        return mPlayers;
    }

    public void setPlayers(Player[] players) {
        mPlayers = players;
    }

    public Score[] getScores() {
        return mScores;
    }

    public void setScores(Score[] scores) {
        mScores = scores;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    private Score[] mScores;
    private Course mCourse;
}
