package com.ba.converter;

import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthoritiesDtoConverter {

    public static List<AuthoritiesDTO> authListToAuthDTOList(List<Authorities> authList){
        List<AuthoritiesDTO> authDTO = new ArrayList<>();
        for(Authorities authorities:authList){
            AuthoritiesDTO auth = new AuthoritiesDTO();
            auth.setAuthority(authorities.getAuthority());
            auth.setUsername(authorities.getUsername());
            authDTO.add(auth);
        }
        return authDTO;
    }
    public static Authorities authDTOaddAuth(AuthoritiesDTO authDto){
        Authorities auth = new Authorities();
        auth.setAuthority(authDto.getAuthority());
        auth.setUsername(authDto.getUsername());
        return auth;

    }
    public static String authDTOdeleteAuth(String username){
        Authorities auth = new Authorities();
        auth.setUsername(username);
        return auth.getUsername();
    }
    public static AuthoritiesDTO authDTOgetByID(Optional<Authorities> dtoList){
        AuthoritiesDTO dto = new AuthoritiesDTO();
        dto.setUsername(dtoList.get().getUsername());
        dto.setAuthority(dtoList.get().getAuthority());
        return dto;
    }

    }



