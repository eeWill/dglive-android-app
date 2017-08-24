package com.evwill.dglive.models;

import java.util.List;

import static android.R.attr.x;

public class Round {
    private List<Player> mPlayers;
    private Course mCourse;
    private int currentHoleNumber = 1;

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
}
