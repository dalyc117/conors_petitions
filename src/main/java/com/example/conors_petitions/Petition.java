package com.example.conors_petitions;

public class Petition {
    String name;
    String[] users;

    public Petition() {
        this.name = "No name given";
        this.users = null;
    }

    public Petition(String name, String[] users) {
        this.name = name;
        this.users = users;
    }
}
