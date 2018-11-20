package com.dicegame;

import com.dicegame.Exceptions.AlreadyExistsException;
import com.dicegame.Exceptions.NotFoundException;
import com.dicegame.Model.PlayResult;
import com.dicegame.Model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DiceGameController
{
    GameController gameController = new GameController();

    @GetMapping(value = "/players")
    public List<Player> listPlayers() throws NotFoundException
    {
        return gameController.listPlayers();
    }

    @PostMapping(value = "/players")
    public String createPlayer(@RequestParam(value = "name" , required = false, defaultValue = "") String name) throws AlreadyExistsException
    {
        return "Player: " + gameController.createPlayer(name) + " created.";
    }

    @PutMapping(value = "/players/{id}")
    public String editPlayerName(@PathVariable("id") Integer id, @RequestParam("name") String name)
    {
        gameController.editPlayerName(id, name);
        return "Player name with id: " + id + " changed to: " + name;
    }

    @DeleteMapping(value = "/players/{id}")
    public String deletePlayer(@RequestParam("id") Integer id)
    {
        gameController.deletePlayer(id);
        return "Player name with id: " + id + " deleted.";
    }

    @GetMapping(value = "/players/{id}/games")
    public List<PlayResult> listPlayerResults(@PathVariable("id") int id)
    {
        return gameController.listPlayerResults(id);
    }

    @PostMapping(value = "/players/{id}/games")
    public String playGame(@PathVariable("id") int id)
    {
        return gameController.play(id);
    }

    @DeleteMapping(value = "/players/{id}/games")
    public String deletePlays(@PathVariable("id") int id)
    {
        gameController.deletePlays(id);

        return "Plays deleted from player with id: " + id;
    }

    @GetMapping(value = "/players/ranking")
    public String getAverageSuccessRate() throws NotFoundException
    {
        return "Average success rate is: " + gameController.getAverageSuccessRate();
    }

    @GetMapping(value = "/players/ranking/winner")
    public String getBestSuccessRate() throws NotFoundException
    {
        return gameController.getBestSuccessRate();
    }

    @GetMapping(value = "/players/ranking/loser")
    public String getWorstSuccessRate() throws NotFoundException
    {
        return gameController.getWorstSuccessRate();
    }
}