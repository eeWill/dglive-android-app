package com.evwill.dglive;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.evwill.dglive.models.Player;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;


@RunWith(AndroidJUnit4.class)
public class RoundActivityUITest {
    @Rule
    public ActivityTestRule<RoundActivity> activityTestRule = new ActivityTestRule<>(RoundActivity.class);

    @Test
    public void testFirstHoleParIsCorrect() throws Exception {
        String holeOnePar = "4";
        onView(withId(R.id.hole_par_label)).check(matches(withText(holeOnePar)));
    }

    @Test
    public void testFirstHoleNumberIsCorrect() throws Exception {
        String holeName = "1";
        onView(withId(R.id.hole_name_label)).check(matches(withText(holeName)));
    }

    @Test
    public void testClickingNextHoleDisplaysCorrectPar() throws Exception {
        String holeTwoPar = "4";
        onView(withId(R.id.next_hole_button)).perform(click());
        onView(withId(R.id.hole_par_label)).check(matches(withText(holeTwoPar)));
    }

    @Test
    public void testClickingNextHoleDisplaysCorrectName() throws Exception {
        String holeTwoName = "2";
        onView(withId(R.id.next_hole_button)).perform(click());
        onView(withId(R.id.hole_name_label)).check(matches(withText(holeTwoName)));
    }

    @Test
    public void testClickingNextHoleTwiceDisplaysHoleThree() throws Exception {
        String holeThreeName = "3";
        clickNextHoleMultipleTimes(2);
        onView(withId(R.id.hole_name_label)).check(matches(withText(holeThreeName)));
    }

    @Test
    public void testClickingPreviousHoleStaysAtHoleOne() throws Exception {
        String holeOneName = "1";
        onView(withId(R.id.previous_hole_button)).perform(click());
        onView(withId(R.id.hole_name_label)).check(matches(withText(holeOneName)));
    }

    @Test
    public void testClickingNextHoleStaysAtHole18() throws Exception {
        String holeEighteenName = "18";
        clickNextHoleMultipleTimes(18);
        onView(withId(R.id.hole_name_label)).check(matches(withText(holeEighteenName)));
    }

    @Test
    public void testClickingAddPlayerShowsAddPlayerDialog() throws Exception {
        String buttonText = "Submit";
        onView(withId(R.id.add_player_button)).perform(click());
        onView(withId(R.id.player_name_submit)).check(matches(withText(buttonText)));
    }

    /* Tests for the add existing player dialog */
    @Test
    public void testThatTheDialogHasDefaultPlayer() throws Exception {
        String name = "Evan";
        onView(withId(R.id.add_existing_player_button)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(hasDescendant(withText(name))));
    }

    @Test
    public void testThatTheDialogHasMultiplePlayers() throws Exception {
        clickAddPlayerDialogAndSubmitName("Troy");
        onView(withId(R.id.add_existing_player_button)).perform(click());
        onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(2));
    }

    @Test
    public void testClickingAnExistingPlayerAddsThemToRoundPlayerList() throws Exception {
        String name = "Evan";
        onView(withId(R.id.add_existing_player_button)).perform(click());
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onData(instanceOf(Player.class))
                .atPosition(1)
                .onChildView(allOf(withId(R.id.player_name_label), withText(name)))
                .check(matches(withText(name)));

    }

    @Test
    public void testSubmittingPlayerNameAddsPlayerToList() throws Exception {
        String name = "Evan";
        clickAddPlayerDialogAndSubmitName(name);
        onData(instanceOf(Player.class))
                .atPosition(1)
                .onChildView(allOf(withId(R.id.player_name_label), withText(name)))
                .check(matches(withText(name)));
    }

    @Test
    public void testAddingToAPlayersScore() throws Exception {
        String playerScore = "4";
        onData(instanceOf(Player.class))
                .atPosition(0)
                .onChildView(allOf(withId(R.id.increase_score_button)))
                .perform(click());
        onData(instanceOf(Player.class))
                .atPosition(0)
                .onChildView(allOf(withId(R.id.player_score_label)))
                .check(matches(withText(playerScore)));

    }

    @Test
    public void testSubtractingFromAPlayersScore() throws Exception {
        String playerScore = "2";
        onData(instanceOf(Player.class))
                .atPosition(0)
                .onChildView(allOf(withId(R.id.decrease_score_button)))
                .perform(click());
        onData(instanceOf(Player.class))
                .atPosition(0)
                .onChildView(allOf(withId(R.id.player_score_label)))
                .check(matches(withText(playerScore)));

    }


    private void clickNextHoleMultipleTimes(Integer count) {
        for(int i = 0; i < count; i++) {
            onView(withId(R.id.next_hole_button)).perform(click());
        }
    }

    private void clickAddPlayerDialogAndSubmitName(String name) {
        onView(withId(R.id.add_player_button)).perform(click());
        onView(withId(R.id.player_name_input)).perform(typeText(name));
        onView(withId(R.id.player_name_submit)).perform(click());
    }

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }

}
