package com.dicegame;

import com.dicegame.Exceptions.AlreadyExistsException;
import com.dicegame.Exceptions.NotFoundException;
import com.dicegame.Model.Dice;
import com.dicegame.Model.PlayResult;
import com.dicegame.Model.Player;
import com.dicegame.Persistence.PlayerReposiroty;
import com.dicegame.Utils.Utils;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameController
{
    PlayerReposiroty playerReposiroty = PlayerReposiroty.getInstance();

    public GameController() {}

    public List<Player> listPlayers() throws NotFoundException {
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

        for (int i = 0; i < 2; i++) playResult.addDiceValue(new Dice().rollDice().getRollValue());

        playerReposiroty.getPlayer(id).addPlayResult(playResult);

        if (playResult.isWin()) return "You Win!";
        else return "You Lose!";
    }

    public void deletePlays(int id) {
        playerReposiroty.getPlayer(id).deletePlays();
    }

    public void deletePlayer(Integer id) {
        playerReposiroty.removePlayer(id);
    }

    public List<PlayResult> listPlayerResults(int id) {
        return playerReposiroty.getPlayer(id).listPlayResults();
    }

    public String getAverageSuccessRate() throws NotFoundException
    {
        double successSum = 0;
        int havePlayedCount = 0;

        for (Player p : playerReposiroty.getPlayers())
        {
            if (p.successRate() != 101)
            {
                successSum = successSum + p.successRate();
                havePlayedCount++;
            }
        }

        String resultString;
        if (havePlayedCount == 0) resultString = "Nobody has played yet";
        else
        {
            double avgSuccessRate = successSum / havePlayedCount;
            resultString = Utils.roundDoubleToString(avgSuccessRate);
        }

        return resultString;
    }

    public Map<String, Object> getBestSuccessRate() throws NotFoundException
    {
        Map<String, Object> map = new HashMap<>();

        List<Player> list = playerReposiroty.getPlayers();
        list.sort(new SortByRate());

        int i = list.size() - 1;
        boolean found = false;
        while (!found && i >= 0)
        {
            if (list.get(i).successRate() != 101.0) found = true;
            else i--;
        }

        if (i >= 0)
        {
            map.put("PlayerId", list.get(i).getId());
            map.put("Success Rate", Utils.roundDoubleToString(list.get(i).successRate()));
        }
        else
        {
            map.put("PlayerId", "Nobody has played yet");
            map.put("Success Rate", "Nobody has played yet");
        }

        return map;
    }

    public String getWorstSuccessRate() throws NotFoundException
    {
        List<Player> list = playerReposiroty.getPlayers();
        list.sort(new SortByRate());

        double worstRate = list.get(0).successRate();

        if (worstRate == 101.0) return "Nobody has played yet";
        else return Utils.roundDoubleToString(worstRate);
    }

    private class SortByRate implements Comparator<Player> {
        public int compare(Player a, Player b) {
            return (int) (a.successRate() - b.successRate());
        }
    }
}