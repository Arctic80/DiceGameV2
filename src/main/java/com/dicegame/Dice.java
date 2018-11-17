package com.dicegame;

import java.util.concurrent.ThreadLocalRandom;


public class Dice
{
    private int rollValue;
    private ThreadLocalRandom randomizer = ThreadLocalRandom.current();

    public Dice(){}

    public Dice rollDice()
    {
        rollValue = randomizer.nextInt(1,8);
        return this;
    }

    public int getRollValue()
    {
        return rollValue;
    }
}

