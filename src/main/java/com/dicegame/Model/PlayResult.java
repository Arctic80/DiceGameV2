package com.dicegame.Model;

import java.util.ArrayList;
import java.util.List;


public class PlayResult
{
    private List<Integer> diceValues = new ArrayList<>();
    private boolean win = true;
    private int winnerNumber = 0;

    public PlayResult() {}

    public void addDiceValue(Integer value)
    {
        if (winnerNumber == 0 && (value == 5 || value == 6)) winnerNumber = value;
        else win = win && (value == winnerNumber);

        diceValues.add(value);
    }

    public boolean isWin() {
        return win;
    }

    // JSON
    public List<Integer> getDiceValues() {
        return diceValues;
    }
}