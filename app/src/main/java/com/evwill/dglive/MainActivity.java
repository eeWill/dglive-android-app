package com.evwill.dglive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    public Button startRoundButton;
    public Button settingsPageButton;
    public Button startPlayersActivityButton;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);

        startRoundButton = (Button)findViewById(R.id.start_round_button);
        settingsPageButton = (Button)findViewById(R.id.settings_button);
        startPlayersActivityButton = (Button)findViewById(R.id.start_players_activity_button);

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

        startPlayersActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startPlayersActivity();
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

    @Override
    public void startPlayersActivity() {
        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);
    }

}
