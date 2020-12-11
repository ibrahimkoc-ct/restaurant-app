package com.ba.dto;

import com.ba.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private List<RoleDTO> roles=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserDTO setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }
}
