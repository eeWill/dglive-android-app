package com.evwill.dglive;

public class MainActivityPresenter {
    MainActivityView view;

    public MainActivityPresenter(MainActivityView view) {
        this.view = view;
    }

    public void startSettingsActivity() {
        view.startSettingsActivity();
    }

    public void startRoundActivity() {
        view.startRoundActivity();
    }
}
