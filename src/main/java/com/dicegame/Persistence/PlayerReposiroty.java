package com.dicegame.Persistence;

import com.dicegame.Exceptions.NotFoundException;
import com.dicegame.Model.Player;

import java.util.*;


public class PlayerReposiroty
{
    private static PlayerReposiroty instance = new PlayerReposiroty();
    Map<Integer, Player> players = new HashMap<>();
    Collection<String> registeredNames = new ArrayList<>();


    private PlayerReposiroty() {}

    public static PlayerReposiroty getInstance()
    {
        return instance;
    }

    public List<Player> getPlayers() throws NotFoundException
    {
        if (players.isEmpty()) throw new NotFoundException();

        return new ArrayList(players.values());
    }

    public Player getPlayer(Integer id)
    {
        return players.get(id);
    }

    public void addPlayer(Integer id, Player player)
    {
        players.put(id, player);
        registeredNames.add(player.getName());
    }

    public void removePlayer(Integer id)
    {
        players.remove(id);
    }

    public void updatePlayer(Integer id, String name)
    {
        Player player = players.get(id);
        player.setName(name);
        players.put(id, player);
    }

    public int size()
    {
        return players.size();
    }

    public boolean contains(String name)
    {
        return registeredNames.contains(name);
    }
}