package com.example.statsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StatsController {
    @Autowired
    private RestTemplate template;

    @GetMapping("/team-stats/{teamId}")
    public String getTeamStats(@PathVariable int teamId) {
        return template.getForObject("http://TeamService/teams/" + teamId, String.class);
    }

    @GetMapping("/player-stats/{playerId}")
    public String getPlayerStats(@PathVariable int playerId) {
        return template.getForObject("http://PlayerService/players/" + playerId, String.class);
    }
}
