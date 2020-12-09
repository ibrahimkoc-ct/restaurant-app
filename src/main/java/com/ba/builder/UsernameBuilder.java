package com.ba.builder;

public abstract class UsernameBuilder<T> {
    public abstract T build();
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
