package com.evwill.dglive;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evwill.dglive.adapters.PlayerScoreAdapter;
import com.evwill.dglive.data.CourseGenerator;
import com.evwill.dglive.models.Course;
import com.evwill.dglive.models.Hole;
import com.evwill.dglive.models.Player;
import com.evwill.dglive.models.Round;
import com.evwill.dglive.models.Score;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoundActivity extends ListActivity implements AdapterHandler, AddPlayerDialog.OnPlayerNameSubmitListener {

    @BindView(R.id.previous_hole_button) ImageButton previousHoleButton;
    @BindView(R.id.next_hole_button) ImageButton nextHoleButton;
    @BindView(R.id.add_player_button) Button addPlayerButton;
    @BindView(R.id.hole_name_label) TextView holeNameLabel;
    @BindView(R.id.hole_par_label) TextView holeParLabel;
    @BindView(R.id.course_name_label) TextView courseNameLabel;
    private Round mRound;
    private CourseGenerator courseGenerator;
    private DialogFragment addPlayerDialog;
    private PlayerScoreAdapter adapter;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        ButterKnife.bind(this);

        courseGenerator = new CourseGenerator();
        course = courseGenerator.campgawBlacks();

        mRound = createNewRound(course);
        courseNameLabel.setText(mRound.getCourse().getName());

        //List adapter for changing player scores on each hole
        adapter = new PlayerScoreAdapter(this, mRound);
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

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayerDialog = new AddPlayerDialog();
                FragmentManager fragmentManager = getFragmentManager();
                addPlayerDialog.show(fragmentManager, "addPlayerDialog");
            }
        });
    }

    @Override
    public void incrementPlayerScore(int playerNumber) {
        int currentHoleNumber = mRound.getCurrentHoleNumber() - 1;
        Score score =  mRound.getPlayers().get(playerNumber).getScores().get(currentHoleNumber);
        score.incrementScore();
        Log.i("Log", String.valueOf(score.getScore()));
        mRound.getPlayers().get(playerNumber).setScoreByHoleNumber(currentHoleNumber, score);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void decrementPlayerScore(int playerNumber) {
        int currentHoleNumber = mRound.getCurrentHoleNumber() - 1;
        Score score =  mRound.getPlayers().get(playerNumber).getScores().get(currentHoleNumber);
        score.decrementScore();
        mRound.getPlayers().get(playerNumber).setScoreByHoleNumber(currentHoleNumber, score);
        adapter.notifyDataSetChanged();
    }

    private Round createNewRound(Course course) {
        List<Player> players = new ArrayList<>();
        Player defaultPlayer = new Player("Evan", allThreesScoreList(course));
        players.add(defaultPlayer);

        Round round = new Round();
        round.setCourse(course);
        round.setPlayers(players);

        return round;
    }

    private List<Score> allThreesScoreList(Course course) {
        Integer strokes = 3;
        List<Score> scores = new ArrayList<>();
        List<Hole> holes = course.getHoles();

        for (int i = 0; i < holes.size(); i++) {
            Score score = new Score(strokes, holes.get(i));
            scores.add(score);
        }

        return scores;

    }

    private void setCurrentHoleInformation() {
        int currentHoleNumber = mRound.getCurrentHoleNumber();
        String currentHoleNumberString = String.valueOf(currentHoleNumber);
        holeNameLabel.setText(currentHoleNumberString);
        int holePar = mRound.getCourse().getHoles().get(currentHoleNumber - 1).getPar();
        holeParLabel.setText(String.valueOf(holePar));
    }

    @Override
    public void onPlayerNameSubmit(String name) {
        List<Score> scores = allThreesScoreList(course);
        Player player = new Player(name, scores);
        mRound.addPlayer(player);
        adapter.notifyDataSetChanged();
    }
}


