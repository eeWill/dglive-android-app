package com.evwill.dglive;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evwill.dglive.adapters.PlayerScoreAdapter;
import com.evwill.dglive.data.CourseGenerator;
import com.evwill.dglive.db.PlayerDataSource;
import com.evwill.dglive.listener.OnPlayerNameSelectListener;
import com.evwill.dglive.listener.OnPlayerNameSubmitListener;
import com.evwill.dglive.models.Course;
import com.evwill.dglive.models.Hole;
import com.evwill.dglive.models.Player;
import com.evwill.dglive.models.Round;
import com.evwill.dglive.models.Score;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoundActivity extends ListActivity implements AdapterHandler, OnPlayerNameSubmitListener, OnPlayerNameSelectListener {

    @BindView(R.id.previous_hole_button) ImageButton previousHoleButton;
    @BindView(R.id.next_hole_button) ImageButton nextHoleButton;
    @BindView(R.id.add_player_button) Button addPlayerButton;
    @BindView(R.id.add_existing_player_button) Button addExistingPlayerButton;
    @BindView(R.id.hole_name_label) TextView holeNameLabel;
    @BindView(R.id.hole_par_label) TextView holeParLabel;
    @BindView(R.id.course_name_label) TextView courseNameLabel;
    private Round mRound;
    private CourseGenerator courseGenerator;
    private DialogFragment addPlayerDialog;
    private DialogFragment addExistingPlayerDialog;
    private PlayerScoreAdapter adapter;
    private Course course;
    protected PlayerDataSource playerDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        ButterKnife.bind(this);

        playerDataSource = new PlayerDataSource(RoundActivity.this);
        playerDataSource.open();

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

        addExistingPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExistingPlayerDialog = new AddExistingPlayerDialog();
                FragmentManager fragmentManager = getFragmentManager();
                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList("Players", (ArrayList<? extends Parcelable>) mRound.getPlayers());
                addExistingPlayerDialog.setArguments(bundle);
                addExistingPlayerDialog.show(fragmentManager, "addExistingPlayerDialog");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerDataSource.open();
        Cursor cursor = playerDataSource.selectAllPlayers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerDataSource.close();
    }

    protected void updateList() {

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
        Player defaultPlayer = createPlayer("Evan");
        players.add(defaultPlayer);

        Round round = new Round();
        round.setCourse(course);
        round.setPlayers(players);

        return round;
    }

    private Player createPlayer(String name) {
        return new Player(name, allThreesScoreList(course));
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
        createPlayerAndUpdateList(name);
    }

    @Override
    public void onPlayerNameSelect(String name) {
        createPlayerAndUpdateList(name);
    }

    private void createPlayerAndUpdateList(String name) {
        Player player = createPlayerByName(name);
        addPlayerToRoundAndUpdateList(player);
    }

    private Player createPlayerByName(String name) {
        List<Score> scores = allThreesScoreList(course);
        return new Player(name, scores);
    }

    private void addPlayerToRoundAndUpdateList(Player player) {
        mRound.addPlayer(player);
        adapter.notifyDataSetChanged();
    }
}


