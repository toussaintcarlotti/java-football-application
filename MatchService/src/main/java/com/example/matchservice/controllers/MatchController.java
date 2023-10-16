package com.example.matchservice.controllers;

import com.example.matchservice.models.Match;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MatchController {
    private static ArrayList<Match> matches = new ArrayList<Match>() {{
        add(new Match(1, "Match 1"));
        add(new Match(2, "Match 2"));
        add(new Match(3, "Match 3"));
    }};

    @GetMapping("/matches")
    @HystrixCommand(fallbackMethod = "fallback_getMatches", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ArrayList<Match> getMatches() throws InterruptedException {
        return matches;
    }

    public ArrayList<Match> fallback_getMatches() {
        System.out.println("Fallback method called");
        return new ArrayList<Match>();
    }

    @GetMapping("/matches/{id}")
    public Match getMatch(@PathVariable int id) {
        return matches.stream().filter(team -> team.getId() == id).findFirst().orElse(null);
    }

    @PostMapping("/matches")
    public String createMatch(@RequestBody Match team) {
        matches.add(team);
        return "Match created";
    }

    @PutMapping("/matches/{id}")
    public String updateMatch(@PathVariable int id, @RequestBody Match team) {
        matches.stream().filter(m -> m.getId() == id).findFirst().ifPresent(m -> m.setName(team.getName()));
        return "Match updated";
    }

    @DeleteMapping("/matches/{id}")
    public String deleteMatch(@PathVariable int id) {
        matches.removeIf(team -> team.getId() == id);
        return "Match deleted";
    }
}
