package com.evwill.dglive;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.notNullValue;

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

    private void clickNextHoleMultipleTimes(Integer count) {
        for(int i = 0; i < count; i++) {
            onView(withId(R.id.next_hole_button)).perform(click());
        }
    }


}