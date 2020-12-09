package com.ba.builder;

import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;

public class AuthoritiesDTOBuilder extends UsernameBuilder{
    private String authority;

    public AuthoritiesDTOBuilder authority(String authority){
        this.authority=authority;
        return this;
    }
    public AuthoritiesDTOBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    @Override
    public AuthoritiesDTO build() {
        AuthoritiesDTO authoritiesDTO= new AuthoritiesDTO();
        authoritiesDTO.setUsername(this.getUsername());
        authoritiesDTO.setAuthority(this.authority);
        return authoritiesDTO;
    }
}
