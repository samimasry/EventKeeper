package com.example.eventkeeper;

public class User {

    private String username, email, password;
    private Fullname name ;
    private Address address;

    public User(String username, String email, String password, Fullname name, Address address) {
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

    public Fullname getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
