package com.dicegame;

import java.util.ArrayList;
import java.util.List;


public class PlayResult {

    private List<Integer> diceValues = new ArrayList<>();

    private boolean win;

    public PlayResult() {
        win = true;
    }

    public void addDiceValue(Integer value) {
        diceValues.add(value);
        win = win && (value == 5 || value == 6);
    }

    public boolean isWin() {
        return win;
    }

    public List<Integer> getDiceValues() {
        return diceValues;
    }
}