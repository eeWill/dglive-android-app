package com.evwill.dglive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    public Button startRoundButton;
    public Button settingsPageButton;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);

        startRoundButton = (Button)findViewById(R.id.start_round_button);
        settingsPageButton = (Button)findViewById(R.id.settings_button);

        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startRoundActivity();
            }
        });

        settingsPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startSettingsActivity();
            }
        });
    }

    @Override
    public void startRoundActivity() {
        Intent intent = new Intent(this, RoundActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


}
