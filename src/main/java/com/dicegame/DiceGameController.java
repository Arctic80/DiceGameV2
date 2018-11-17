package com.dicegame;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DiceGameController
{
    GameController gameController = new GameController();

    // /players
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> listPlayers() throws NotFoundException
    {
        return gameController.listPlayers();
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public String createPlayer(@RequestParam("name") String name)
    {
        return "Player: " + gameController.createPlayer(name) + " created.";
    }

    // /players/id
    @RequestMapping(value = "/players/{id]", method = RequestMethod.PUT)
    public String editPlayerName(@RequestParam("id") Integer id, @RequestParam("name") String name) throws NameRequiredException
    {
        gameController.editPlayerName(id, name);
        return "Player name with id: " + id + " changed to: " + name;
    }

    @RequestMapping(value = "/players/{id]", method = RequestMethod.DELETE)
    public String deletePlayer(@RequestParam("id") Integer id)
    {
        gameController.deletePlayer(id);
        return "Player name with id: " + id + " deleted.";
    }

    // /players/id/games
    @RequestMapping(value = "/players/{id}/games", method = RequestMethod.GET)
    public List<PlayResult> listPlayerResults(@PathVariable("id") int id)
    {
        return gameController.listPlayerResults(id);
    }

    @RequestMapping(value = "/players/{id}/games", method = RequestMethod.POST)
    public String playGame(@PathVariable("id") int id)
    {
        return gameController.play(id);
    }

    @RequestMapping(value = "/players/{id}/games", method = RequestMethod.DELETE)
    public String deletePlays(@PathVariable("id") int id)
    {
        gameController.deletePlays(id);

        return "Plays deleted from player with id: " + id;
    }

    // /players/ranking/*
    @RequestMapping(value = "/players/ranking", method = RequestMethod.GET)
    public String getAverageSuccessRate() throws NotFoundException
    {
        return "Average success rate is: " + gameController.getAverageSuccessRate();
    }

    @RequestMapping(value = "/players/ranking/winner", method = RequestMethod.GET)
    public String getBestSuccessRate() throws NotFoundException
    {
        return gameController.getBestSuccessRate();
    }

    @RequestMapping(value = "/players/ranking/loser", method = RequestMethod.GET)
    public String getWorstSuccessRate() throws NotFoundException
    {
        return gameController.getWorstSuccessRate();
    }
}