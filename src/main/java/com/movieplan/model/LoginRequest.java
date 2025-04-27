package com.movieplan.model;

public class LoginRequest {

    private String username;
    private String password;

    // No-arg constructor (required for frameworks like Spring and Jackson)
    public LoginRequest() {
        // Default constructor
    }

    // All-args constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method
    @Override
    public String toString() {
        return "LoginRequest [username=" + (username != null ? username : "null") + ", password=" + password + "]";
    }
}