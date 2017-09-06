package com.evwill.dglive;


import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class RoundActivityTest {

    RoundActivity activity;

    @Before
    public void setUp() throws Exception {
            activity = Robolectric.setupActivity(RoundActivity.class);
    }

    @Test
    public void testRoundStartsWithOnePlayer() throws Exception {
        ListView playerListView = (ListView) activity.findViewById(android.R.id.list);
        assertEquals(1, playerListView.getChildCount());
    }

    @Test
    public void testPlayerCanBeAddedToRound() throws Exception {
        Button addPlayerButton = (Button) activity.findViewById(R.id.add_player_button);
        addPlayerButton.performClick();

        Dialog dialog = ShadowDialog.getLatestDialog();
        Button playerSubmitButton = (Button) dialog.findViewById(R.id.player_name_submit);
        ListView playerListView = (ListView) activity.findViewById(android.R.id.list);
        EditText nameInput = (EditText) dialog.findViewById(R.id.player_name_input);

        nameInput.setText("Evan");
        playerSubmitButton.performClick();

        assertEquals(2, playerListView.getChildCount());
    }

    @Test
    public void testExistingPlayerDialogHasTitle() throws Exception {
        clickAddExistingPlayerButton();
        Dialog dialog = ShadowDialog.getLatestDialog();
        TextView title = (TextView) dialog.findViewById(R.id.add_existing_player_title);
        assertEquals("Add Existing Player", title.getText());
    }

    @Test
    public void testExistingPlayerDialogHasPlayersPopulated() throws Exception {
        clickAddExistingPlayerButton();
        Dialog dialog = ShadowDialog.getLatestDialog();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerView);
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);
        recyclerView.findViewHolderForAdapterPosition(0).itemView.performClick();

        ListView playerListView = (ListView) activity.findViewById(android.R.id.list);
        assertEquals(2, playerListView.getChildCount());

    }

    @Test
    public void testIncrementScoreButtonUpdatesTextView() throws Exception {
        String newScore = "4";
        ImageButton increaseScoreButton = (ImageButton) activity.findViewById(R.id.increase_score_button);
        TextView results = (TextView) activity.findViewById(R.id.player_score_label);
        increaseScoreButton.performClick();
        assertEquals(newScore, results.getText());
    }

    @Test
    public void testDecrementScoreButtonUpdatesText() throws Exception {
        String newScore = "2";
        ImageButton decreaseScoreButton = (ImageButton) activity.findViewById(R.id.decrease_score_button);
        TextView results = (TextView) activity.findViewById(R.id.player_score_label);
        decreaseScoreButton.performClick();
        assertEquals(newScore, results.getText());
    }

    @Test
    public void testDecrementScoreDoesNotUpdateNextHole() throws Exception {
        String newScore = "3";
        ImageButton decreaseScoreButton = (ImageButton) activity.findViewById(R.id.decrease_score_button);
        ImageButton nextHoleButton = (ImageButton) activity.findViewById(R.id.next_hole_button);
        TextView results = (TextView) activity.findViewById(R.id.player_score_label);
        decreaseScoreButton.performClick();
        nextHoleButton.performClick();
        assertEquals(newScore, results.getText());
    }

    @Test
    public void testIncrementScoreDoesNotUpdateNextHole() throws Exception {
        String newScore = "3";
        ImageButton decreaseScoreButton = (ImageButton) activity.findViewById(R.id.increase_score_button);
        ImageButton nextHoleButton = (ImageButton) activity.findViewById(R.id.next_hole_button);
        TextView results = (TextView) activity.findViewById(R.id.player_score_label);
        decreaseScoreButton.performClick();
        nextHoleButton.performClick();
        assertEquals(newScore, results.getText());
    }

    private void clickAddExistingPlayerButton() {
        Button addExistingPlayer = (Button) activity.findViewById(R.id.add_existing_player_button);
        addExistingPlayer.performClick();
    }
}