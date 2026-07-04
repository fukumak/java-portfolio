package com.fukumak.servletapp.model;

import java.time.LocalDateTime;

public class User {

    private final String username;
    private final String passwordHash;
    private final LocalDateTime registeredAt;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.registeredAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
}
