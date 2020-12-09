package com.ba.builder;

import com.ba.dto.UsersDTO;
import com.ba.entity.Users;

public class UsersDTOBuilder extends UsernameBuilder{

    private String password;
    private Boolean enabled;

    public UsersDTOBuilder password(String password){
        this.password=password;
        return this;
    }
    public UsersDTOBuilder enabled(Boolean enabled){
        this.enabled=enabled;
        return this;
    }
    public UsersDTOBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    @Override
    public UsersDTO build() {
        UsersDTO usersDTO= new UsersDTO();
        usersDTO.setPassword(this.password);
        usersDTO.setEnabled(this.enabled);
        usersDTO.setUsername(this.getUsername());
        return usersDTO;
    }
}
