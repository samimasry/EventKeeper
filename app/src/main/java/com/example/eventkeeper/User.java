package com.example.eventkeeper;

public class User {

    private String username, email, password;
    private String[] name = new String[2];
    private String[] address = new String[4];

    public User(String username, String email, String password, String[] name, String[] address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String[] getName() {
        return name;
    }

    public String[] getAddress() {
        return address;
    }
}
