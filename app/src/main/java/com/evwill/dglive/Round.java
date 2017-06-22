package com.evwill.dglive;

public class Round {
    private Player[] mPlayers;
    private Course mCourse;
    private int currentHoleNumber = 1;

    public int getCurrentHoleNumber() {
        return currentHoleNumber;
    }

    public void setCurrentHoleNumber(int currentHoleNumber) {
        this.currentHoleNumber = currentHoleNumber;
    }

    public Player[] getPlayers() {
        return mPlayers;
    }

    public void setPlayers(Player[] players) {
        mPlayers = players;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public void decrementCurrentHoleNumber() {
        this.currentHoleNumber = this.currentHoleNumber - 1;
    }

    public void incrementCurrentHoleNumber() {
        this.currentHoleNumber = this.currentHoleNumber + 1;
    }
}
