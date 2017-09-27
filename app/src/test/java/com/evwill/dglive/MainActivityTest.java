package com.evwill.dglive;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class MainActivityTest {

    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void startRoundActivity() throws Exception {
        // Arrange
        Class roundActivity = RoundActivity.class;
        Intent expectedIntent = new Intent(activity, roundActivity);

        // Act
        activity.startRoundButton.callOnClick();

        // Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void testPlayerActivityStarts() throws Exception {
        Class playersActivity = PlayersActivity.class;
        Intent expectedIntent = new Intent(activity, playersActivity);

        // Act
        activity.startPlayersActivityButton.performClick();

        // Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

}