package com.evwill.dglive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=23)
public class SettingsActivityTest {

    SettingsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(SettingsActivity.class);
    }

    @Test
    public void testActivityHasAddNewPlayerButton() throws Exception {

    }


}