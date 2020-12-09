package com.ba.builder;

import com.ba.entity.Users;

public class UsersBuilder extends UsernameBuilder{

    private String password;
    private Boolean enabled;

    public UsersBuilder password(String password){
        this.password=password;
        return this;
    }
    public UsersBuilder enabled(Boolean enabled){
        this.enabled=enabled;
        return this;
    }
    public UsersBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    @Override
    public Users build() {
        Users users= new Users();
        users.setPassword(this.password);
        users.setEnabled(this.enabled);
        users.setUsername(this.getUsername());
        return users;
    }
}
