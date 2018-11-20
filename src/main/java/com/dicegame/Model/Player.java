package com.dicegame.Model;

import com.dicegame.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class Player
{
    private static Integer lastId = 1;
    private final Integer id;
    private String name;
    private int wins = 0;
    private List<PlayResult> playResults = new ArrayList<>();


    public Player(String name)
    {
        if (name.equals("")) this.name = "ANONYMOUS";
        else this.name = name;
        id = lastId;
        lastId++;
    }

    public void addPlayResult(PlayResult playResult)
    {
        if (playResult.isWin()) wins++;
        playResults.add(playResult);
    }

    public List<PlayResult> listPlayResults()
    {
        return playResults;
    }

    public void deletePlays()
    {
        playResults.clear();
    }

    public double successRate()
    {
        double successRate = ((double) wins / (double) playResults.size()) * 100;
        return successRate;
    }

    public String getSuccessRate()
    {
        double successRate;

        if (playResults.size() <= 0) return "Player hasn't played yet";
        else successRate = ((double) wins / (double) playResults.size()) * 100;

        return Utils.roundDoubleToString(successRate);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}