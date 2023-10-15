package com.example.statsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class StatsController {
    @Autowired
    private RestTemplate template;

    @GetMapping("/teams")
    public ArrayList test() {
        return template.getForObject("http://TeamService/teams", ArrayList.class);
    }
}
