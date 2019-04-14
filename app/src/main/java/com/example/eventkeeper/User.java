package com.example.eventkeeper;

import com.google.gson.annotations.SerializedName;

public class User {

    private String  username, email, password;
    @SerializedName("userid")
    private String userId;
    @SerializedName("token")
    private String token;
    private Fullname name ;
    private Address address;

    public User(String userId, String token ,String username, String email, String password, Fullname name, Address address) {
        this.userId = userId;
        this.token = token;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }


    public String getUsername() {
        return username;
    }
    public String getToken() {
        return token;
    }
    public String getUserId() {
        return userId;
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
