package com.evwill.dglive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void startSettingsActivity() throws Exception {
        presenter.startSettingsActivity();
        Mockito.verify(view).startSettingsActivity();
    }

    @Test
    public void startRoundActivity() throws Exception {
        presenter.startRoundActivity();
        Mockito.verify(view).startRoundActivity();
    }

}