package com.evwill.dglive;

class Score {
    private int mScore;
    private Player mPlayer;
    private Hole mHole;

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }

    public Hole getHole() {
        return mHole;
    }

    public void setHole(Hole hole) {
        mHole = hole;
    }
}
