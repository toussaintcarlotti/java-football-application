package com.example.matchservice.models;

public class Match {
    private Integer id;

    private String name;

    public Match() {}

    public Match(Integer id, String name) {
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

