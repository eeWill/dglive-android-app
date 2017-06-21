package com.evwill.dglive;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.evwill.dglive.adapters.PlayerScoreAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundActivity extends ListActivity {

    private Round mRound;
    private TextView holeNameLabel;
    private TextView holeParLabel;
    private TextView courseNameLabel;
    private Button previousHoleButton;
    private Button nextHoleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        previousHoleButton = (Button)findViewById(R.id.previous_hole_button);
        nextHoleButton = (Button)findViewById(R.id.next_hole_button);
        holeNameLabel = (TextView)findViewById(R.id.hole_name_label);
        holeParLabel = (TextView)findViewById(R.id.hole_par_label);
        courseNameLabel = (TextView)findViewById(R.id.course_name_label);

        //Create a new round
        mRound = createNewRound();
        courseNameLabel.setText(mRound.getCourse().getName());

        //List adapter for changing player scores on each hole
        PlayerScoreAdapter adapter = new PlayerScoreAdapter(this, mRound.getPlayers());
        setListAdapter(adapter);

        //Set the current hole name and par
        setCurrentHoleInformation();

        //Set OnClick listeners for next and previous holes
        previousHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRound.decrementCurrentHoleNumber();
                setCurrentHoleInformation();
            }
        });

        nextHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRound.incrementCurrentHoleNumber();
                setCurrentHoleInformation();
            }
        });
    }

    private Round createNewRound() {
        //Create some players, this is for development purposes
        Player player = new Player();
        player.setName("Renee");
        Player player2 = new Player();
        player2.setName("Troy");
        Player player3 = new Player();
        player3.setName("Evan");
        Player[] players = {player, player2, player3};

        //Create a course
        Course course = createNewCourse();

        //Create round, add player and course
        Round round = new Round();
        round.setCourse(course);
        round.setPlayers(players);

        return round;
    }

    private Course createNewCourse() {
        Course course = new Course();

        //Random par for development purposes
        Random random = new Random();
        List<Hole> holes = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            int randomInt = random.nextInt((5 - 3) + 1) + 3;
            Hole hole = new Hole(String.valueOf(i), randomInt);
            holes.add(hole);
        }

        course.setHoles(holes);
        course.setName("Campgaw Greens");


        return course;
    }

    private void setCurrentHoleInformation() {
        int currentHoleNumber = mRound.getCurrentHoleNumber();
        String currentHoleNumberString = String.valueOf(currentHoleNumber);
        holeNameLabel.setText(currentHoleNumberString);
        int holePar = mRound.getCourse().getHoles().get(currentHoleNumber).getPar();
        holeParLabel.setText(String.valueOf(holePar));
    }

}
