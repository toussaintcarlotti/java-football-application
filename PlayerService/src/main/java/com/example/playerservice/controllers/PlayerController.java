package com.example.playerservice.controllers;

import com.example.playerservice.models.Player;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PlayerController {
    private static ArrayList<Player> players = new ArrayList<Player>() {{
        add(new Player(1, "Player 1"));
        add(new Player(2, "Player 2"));
        add(new Player(3, "Player 3"));
    }};

    @GetMapping("/players")
    @HystrixCommand(fallbackMethod = "fallback_getPlayers", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ArrayList<Player> getPlayers() throws InterruptedException {
        return players;
    }

    public ArrayList<Player> fallback_getPlayers() {
        System.out.println("Fallback method called");
        return new ArrayList<Player>();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id) {
        return players.stream().filter(team -> team.getId() == id).findFirst().orElse(null);
    }

    @PostMapping("/players")
    public String createPlayer(@RequestBody Player team) {
        players.add(team);
        return "Player created";
    }

    @PutMapping("/players/{id}")
    public String updatePlayer(@PathVariable int id, @RequestBody Player team) {
        players.stream().filter(m -> m.getId() == id).findFirst().ifPresent(m -> m.setName(team.getName()));
        return "Player updated";
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id) {
        players.removeIf(team -> team.getId() == id);
        return "Player deleted";
    }
}
