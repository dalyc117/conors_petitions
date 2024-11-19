package com.example.conors_petitions;

public class Petition {
    String name;
    String[] users;
    int id;

    public Petition() {
        this.name = "No name given";
        this.users = null;
        this.id = 0;
    }

    public Petition(String name, String[] users, int id) {
        this.name = name;
        this.users = users;
        this.id = id;
    }

    public String toString() {
        return name + "  ,  " + users.length + "  ;  ";
    }
    public int getId() {
        return this.id;
    }
}
