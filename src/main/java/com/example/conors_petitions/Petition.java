package com.example.conors_petitions;

import java.util.List;

public class Petition {
    String name;
    List<User> users;
    static int highestId = 0;
    int id;

    public Petition() {
        this.name = "No name given";
        this.users = null;
        highestId++;
        this.id = highestId;
    }

    public Petition(String name, List<User> users) {
        this.name = name;
        this.users = users;
        highestId++;
        this.id = highestId;
    }

    public void addUser(User newUsers){
        users.add(newUsers);
    }

    public int getCount(){
        int countUsers = 0;
        for (User user : users) {
            countUsers = countUsers + 1;
        }
        return countUsers;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
    return name;
    }
}
