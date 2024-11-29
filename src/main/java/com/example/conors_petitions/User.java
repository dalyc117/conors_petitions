package com.example.conors_petitions;

public class User {
    String username;
    String email;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public String getName(){
        return username;
    }

    public String getEmail(){
        return email;
    }
}
