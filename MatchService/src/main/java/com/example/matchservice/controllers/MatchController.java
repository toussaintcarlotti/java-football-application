package com.example.matchservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    @GetMapping("matches")
    public String Matches()
    {
        System.out.println("liste des matches");
        return "liste des matches";
    }
}
