package com.example.conors_petitions;

public class Petition {
    String name;
    String[] users;
    static int highestId = 0;
    int id;

    public Petition() {
        this.name = "No name given";
        this.users = null;
        highestId++;
        this.id = highestId;
    }

    public Petition(String name, String[] users) {
        this.name = name;
        this.users = users;
        highestId++;
        this.id = highestId;
    }

    public String toString() {
        return name + "  ,  " + users.length + "  ;  " + id;
    }
    public int getId() {
        return this.id;
    }
}
