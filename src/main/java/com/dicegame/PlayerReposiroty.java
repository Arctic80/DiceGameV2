package com.dicegame;

import java.util.*;


public class PlayerReposiroty
{
    private static PlayerReposiroty instance;

    Map<Integer, Player> players = new HashMap<>();

    private PlayerReposiroty()
    {
    }

    public static PlayerReposiroty getInstance()
    {
        if (instance == null) instance = new PlayerReposiroty();
        return instance;
    }

    public List<Player> getPlayers() throws NotFoundException
    {
        if (players.size() == 0) throw new NotFoundException();

        return new ArrayList(players.values());
    }

    public Player getPlayer(Integer id)
    {
        return players.get(id);
    }

    public void addPlayer(Integer id, Player player)
    {
        players.put(id, player);
    }

    public void removePlayer(Integer id)
    {
        players.remove(id);
    }

    public void updatePlayer(Integer id, String name) {

        Player player = players.get(id);
        player.setName(name);
        players.put(id, player);
    }

    public int size(){

        return players.size();
    }
}