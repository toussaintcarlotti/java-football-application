package com.example.teamservice.controllers;

import com.example.teamservice.models.Team;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@RestController
public class TeamController {
    @Autowired
    private RestTemplate template;

    private static ArrayList<Team> teams = new ArrayList<Team>() {{
        add(new Team(1, "Team 1"));
        add(new Team(2, "Team 2"));
        add(new Team(3, "Team 3"));
    }};

    @GetMapping("/teams")
    @HystrixCommand(fallbackMethod = "fallback_getTeams", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ArrayList<Team> getTeams() throws InterruptedException {
        return teams;
    }

    public ArrayList<Team> fallback_getTeams() {
        System.out.println("Fallback method called");
        return new ArrayList<Team>();
    }

    @GetMapping("/teams/{id}")
    public Team getTeam(@PathVariable int id) {
        return teams.stream().filter(team -> team.getId() == id).findFirst().orElse(null);
    }

    @PostMapping("/teams")
    public String createTeam(@RequestBody Team team) {
        teams.add(team);
        return "Team created";
    }

    @PutMapping("/teams/{id}")
    public String updateTeam(@PathVariable int id, @RequestBody Team team) {
        teams.stream().filter(m -> m.getId() == id).findFirst().ifPresent(m -> m.setName(team.getName()));
        return "Team updated";
    }

    @DeleteMapping("/teams/{id}")
    public String deleteTeam(@PathVariable int id) {
        teams.removeIf(team -> team.getId() == id);
        return "Team deleted";
    }

    @GetMapping("/test")
    public String test() {
        return "working....";
    }

}
