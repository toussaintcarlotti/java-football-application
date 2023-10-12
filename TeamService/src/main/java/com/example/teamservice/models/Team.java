package com.example.teamservice.models;

public class Team {
    private Integer id;

    private String name;

    public Team() {}

    public Team(Integer id, String name) {
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

