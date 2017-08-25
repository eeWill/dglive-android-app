package com.evwill.dglive.models;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class RoundTest {

    Round round;

    @Before
    public void setUp() throws Exception {
        round = new Round();
    }

    @Test
    public void testAddPlayer() throws Exception {
        String playerName = "Evan";
        List<Score> scores = new ArrayList<>();

        Player player = new Player(playerName, scores);
        round.addPlayer(player);

        assertEquals(playerName, round.getPlayers().get(0).getName());
    }

}