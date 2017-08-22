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
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class RoundActivityUITest {
    @Rule
    public ActivityTestRule<RoundActivity> activityTestRule = new ActivityTestRule<>(RoundActivity.class);

    @Test
    /*
    public void startRoundActivity() throws Exception {
        String otherActivityString = "Campgaw Greens";
        onView(withId(R.id.start_round_button)).perform(click());
        onView(withText(otherActivityString)).check(matches(notNullValue()));
    }
    */

}
