package com.ba.builder;

import com.ba.dto.RoleDTO;
import com.ba.dto.UserDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTOBuilder extends IdBuilder {
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private List<RoleDTO> roles=new ArrayList<>();


    public UserDTOBuilder id(Long id) {
        this.setId(id);
        return this;
    }
    public UserDTOBuilder email(String email){
        this.email=email;
        return this;
    }
    public UserDTOBuilder username(String username){
        this.username=username;
        return this;
    }
    public UserDTOBuilder password(String password){
        this.password=password;
        return this;
    }
    public UserDTOBuilder enabled(boolean enabled){
        this.enabled=enabled;
        return this;
    }
    public UserDTOBuilder roles(List<RoleDTO> roles){
        this.roles=roles;
        return this;
    }
    @Override
    public UserDTO build() {
        UserDTO dto= new UserDTO();
        dto.setRoles(this.roles);
        dto.setUsername(this.username);
        dto.setPassword(this.password);
        dto.setEnabled(this.enabled);
        dto.setEmail(this.email);
        dto.setId(this.getId());
        return dto;
    }
}
