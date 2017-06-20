package com.evwill.dglive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RoundActivity extends AppCompatActivity {

    private Round mRound;
    private TextView playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        playerName = (TextView)findViewById(R.id.playerName);

        //Create a new round
        mRound = createNewRound();

        //Get the players and add them to the UI
        Player[] currentPlayer = mRound.getPlayers();
        playerName.setText(currentPlayer[0].getName());
    }

    private Round createNewRound() {
        Player player = new Player();
        player.setName("Renee");

        Player[] players = {player};

        Round round = new Round();
        round.setPlayers(players);

        return round;
    }
}
