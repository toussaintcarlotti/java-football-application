package com.example.playerservice.models;

public class Player {
    private Integer id;

    private String name;

    public Player() {}

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

