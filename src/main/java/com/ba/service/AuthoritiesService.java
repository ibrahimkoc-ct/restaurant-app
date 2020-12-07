package com.ba.service;

import com.ba.converter.AuthoritiesDtoConverter;
import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;
import com.ba.entity.Users;
import com.ba.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    public String deleteAuth(String username) {

        authoritiesRepository.deleteById(AuthoritiesDtoConverter.authDTOdeleteAuth(username));
        return "kisi silindi";

    }

    //yapıldı
    public AuthoritiesDTO updateAuth(AuthoritiesDTO authDto) {

        authoritiesRepository.saveAndFlush(AuthoritiesDtoConverter.authDTOaddAuth(authDto));
        return authDto;

    }


    public String addAuth(AuthoritiesDTO authDto) {
        authoritiesRepository.save(AuthoritiesDtoConverter.authDTOaddAuth(authDto));
        return "Ekleme başarı ile tamamlandı";
    }


    public AuthoritiesDTO getAuthById(String id) {
        Optional<Authorities> dtoList = authoritiesRepository.findById(id);
    return (AuthoritiesDtoConverter.authDTOgetByID(dtoList));


    }


    public List<AuthoritiesDTO> authoritiesList() {
        List<Authorities> authList = authoritiesRepository.findAll();
        return AuthoritiesDtoConverter.authListToAuthDTOList(authList);


    }
}