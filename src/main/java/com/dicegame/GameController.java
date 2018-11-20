package com.dicegame;

import com.dicegame.Exceptions.AlreadyExistsException;
import com.dicegame.Exceptions.NotFoundException;
import com.dicegame.Model.Dice;
import com.dicegame.Model.PlayResult;
import com.dicegame.Model.Player;
import com.dicegame.Persistence.PlayerReposiroty;
import com.dicegame.Utils.Utils;

import java.util.Comparator;
import java.util.List;


public class GameController
{

    PlayerReposiroty playerReposiroty = PlayerReposiroty.getInstance();

    public GameController()
    {
        PlayerReposiroty.getInstance();
    }

    public List<Player> listPlayers() throws NotFoundException
    {
        return playerReposiroty.getPlayers();
    }

    public String createPlayer(String name) throws AlreadyExistsException
    {
        if (name != "" && playerReposiroty.contains(name)) throw new AlreadyExistsException();

        Player player = new Player(name);
        Integer id = player.getId();

        playerReposiroty.addPlayer(id, player);

        return player.getName();
    }

    public void editPlayerName(Integer id, String name)
    {
        if (name.equals("")) name = "ANONYMOUS";
        playerReposiroty.updatePlayer(id, name);
    }

    public String play(Integer id)
    {
        PlayResult playResult = new PlayResult();

        for (int i = 0; i < 6; i++){
            Dice d = new Dice().rollDice();

            System.out.println("sdad  :" + d.getRollValue());
            playResult.addDiceValue(d.getRollValue());
        }

        playerReposiroty.getPlayer(id).addPlayResult(playResult);

        String result;
        if (playResult.isWin()) result = "You Win!";
        else result = "You Lose!";

        return result;
    }

    public void deletePlays(int id)
    {
        playerReposiroty.getPlayer(id).deletePlays();
    }

    public void deletePlayer(Integer id)
    {
        playerReposiroty.removePlayer(id);
    }

    public List<PlayResult> listPlayerResults(int id)
    {
        return playerReposiroty.getPlayer(id).listPlayResults();
    }

    public String getAverageSuccessRate() throws NotFoundException
    {
        double ratesSum = 0;

        for (Player p : playerReposiroty.getPlayers()) ratesSum = ratesSum + p.successRate();

        double avgSuccessRate = ratesSum / playerReposiroty.size();

        return Utils.roundDoubleToString(avgSuccessRate);
    }

    public String getBestSuccessRate() throws NotFoundException
    {
        List<Player> tempList = playerReposiroty.getPlayers();
        tempList.sort(new SortbyRate());

        return Utils.roundDoubleToString(tempList.get(0).successRate());
    }

    public String getWorstSuccessRate() throws NotFoundException
    {
        List<Player> tempList = playerReposiroty.getPlayers();
        tempList.sort(new SortbyRate());

        return Utils.roundDoubleToString(tempList.get(0).successRate());
    }

    private class SortbyRate implements Comparator<Player>
    {
        public int compare(Player a, Player b)
        {
            return (int) (a.successRate() * 100 - b.successRate() * 100);
        }
    }
}