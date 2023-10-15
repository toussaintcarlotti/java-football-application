package com.example.teamservice.controllers;

import com.example.teamservice.models.Team;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class TeamController {
    private static ArrayList<Team> teams = new ArrayList<Team>() {{
        add(new Team(1, "Team 1"));
        add(new Team(2, "Team 2"));
        add(new Team(3, "Team 3"));
    }};

    @GetMapping("/teams")
    public ArrayList<Team> getTeames() {
        System.out.println("teams: " );
        return teams;
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
