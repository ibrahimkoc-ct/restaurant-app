package com.ba.builder;

import com.ba.entity.Role;
import com.ba.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserBuilder extends IdBuilder {

    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private List<Role> roles = new ArrayList<>();

    public UserBuilder id(Long id) {
        this.setId(id);
        return this;
    }
    public UserBuilder email(String email){
        this.email=email;
        return this;
    }
    public UserBuilder username(String username){
        this.username=username;
        return this;
    }
    public UserBuilder password(String password){
        this.password=password;
        return this;
    }
    public UserBuilder enabled(boolean enabled){
        this.enabled=enabled;
        return this;
    }
    public UserBuilder roles(List<Role> roles){
        this.roles=roles;
        return this;
    }

    @Override
    public User build() {
        User user = new User();
        user.setId(this.getId());
//        user.setRoles(this.roles);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEnabled(this.enabled);
        return user;
    }
}
