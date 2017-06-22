package com.evwill.dglive;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evwill.dglive.adapters.PlayerScoreAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoundActivity extends ListActivity implements AdapterHandler {

    @BindView(R.id.previous_hole_button) Button previousHoleButton;
    @BindView(R.id.next_hole_button) Button nextHoleButton;
    @BindView(R.id.hole_name_label) TextView holeNameLabel;
    @BindView(R.id.hole_par_label) TextView holeParLabel;
    @BindView(R.id.course_name_label) TextView courseNameLabel;
    private Round mRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        ButterKnife.bind(this);

        //Create course
        Course course = createNewCourse();
        //Create a new round
        mRound = createNewRound(course);
        courseNameLabel.setText(mRound.getCourse().getName());

        //List adapter for changing player scores on each hole
        final PlayerScoreAdapter adapter = new PlayerScoreAdapter(this, mRound);
        setListAdapter(adapter);

        //Set the current hole name and par
        setCurrentHoleInformation();

        //Set OnClick listeners for next and previous holes
        previousHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRound.decrementCurrentHoleNumber();
                setCurrentHoleInformation();
                adapter.notifyDataSetChanged();
            }
        });

        nextHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRound.incrementCurrentHoleNumber();
                setCurrentHoleInformation();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void incrementPlayerScore(int playerNumber) {
        int currentHoleNumber = mRound.getCurrentHoleNumber();
        Score score =  mRound.getPlayers()[playerNumber].getScores().get(currentHoleNumber);
        score.incrementScore();
        mRound.getPlayers()[playerNumber].setScoreByHoleNumber(currentHoleNumber, score);
    }

    @Override
    public void decrementPlayerScore(int playerNumber) {
        int currentHoleNumber = mRound.getCurrentHoleNumber();
        Score score =  mRound.getPlayers()[playerNumber].getScores().get(currentHoleNumber);
        score.decrementScore();
        mRound.getPlayers()[playerNumber].setScoreByHoleNumber(currentHoleNumber, score);
    }

    private Round createNewRound(Course course) {
        //Create some players, this is for development purposes
        Player player = new Player();
        player.setName("Renee");
        player.setScores(createDummyScores(course));
        Player player2 = new Player();
        player2.setName("Troy");
        player2.setScores(createDummyScores(course));
        Player player3 = new Player();
        player3.setName("Evan");
        player3.setScores(createDummyScores(course));
        Player[] players = {player, player2, player3};

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

    private List<Score> createDummyScores(Course course) {
        List<Hole> holes = course.getHoles();
        List<Score> scores = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 18; i++) {
            int randomInt = random.nextInt((6 - 2) + 1) + 2;
            Score score = new Score(randomInt, holes.get(i - 1));
            scores.add(score);
        }

        return scores;
    }

    private void setCurrentHoleInformation() {
        int currentHoleNumber = mRound.getCurrentHoleNumber();
        String currentHoleNumberString = String.valueOf(currentHoleNumber);
        holeNameLabel.setText(currentHoleNumberString);
        int holePar = mRound.getCourse().getHoles().get(currentHoleNumber).getPar();
        holeParLabel.setText(String.valueOf(holePar));
    }

}


