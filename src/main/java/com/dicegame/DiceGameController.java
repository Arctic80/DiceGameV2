package com.dicegame;

import com.dicegame.Exceptions.AlreadyExistsException;
import com.dicegame.Exceptions.NotFoundException;
import com.dicegame.Model.PlayResult;
import com.dicegame.Model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class DiceGameController
{
    GameController gameController = new GameController();

    @GetMapping("/players")
    public List<Player> listPlayers() throws NotFoundException
    {
        return gameController.listPlayers();
    }

    @PostMapping("/players")
    public String createPlayer(@RequestParam(required = false, defaultValue = "") String name) throws AlreadyExistsException
    {
        return "Player: " + gameController.createPlayer(name) + " created.";
    }

    @PutMapping("/players/{id}")
    public String editPlayerName(@PathVariable int id, @RequestParam String name)
    {
        gameController.editPlayerName(id, name);
        return "Player name with id: " + id + " changed to: " + name;
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id)
    {
        gameController.deletePlayer(id);
        return "Player name with id: " + id + " deleted.";
    }

    @GetMapping("/players/{id}/games")
    public List<PlayResult> listPlayerResults(@PathVariable int id)
    {
        return gameController.listPlayerResults(id);
    }

    @PostMapping("/players/{id}/games")
    public String playGame(@PathVariable int id)
    {
        return gameController.play(id);
    }

    @DeleteMapping("/players/{id}/games")
    public String deletePlays(@PathVariable int id)
    {
        gameController.deletePlays(id);

        return "Plays deleted from player with id: " + id;
    }

    @GetMapping("/players/ranking")
    public String getAverageSuccessRate() throws NotFoundException
    {
        return "Average success rate is: " + gameController.getAverageSuccessRate();
    }

    @GetMapping("/players/ranking/winner")
    public Map<String, Object> getBestSuccessRate() throws NotFoundException
    {
        return gameController.getBestSuccessRate();
    }

    @GetMapping("/players/ranking/loser")
    public String getWorstSuccessRate() throws NotFoundException
    {
        return gameController.getWorstSuccessRate();
    }
}