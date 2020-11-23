package com.ba.service;

import com.ba.entity.Authorities;
import com.ba.entity.Users;
import com.ba.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    public void loadAdmin(Authorities auth){
        authoritiesRepository.save(auth);
    }

    public List<Authorities> authoritiesList(){
        return authoritiesRepository.findAll();
    }

    public void addAuth(Authorities auth){
        authoritiesRepository.save(auth);
    }

    public List<Authorities> deleteAuth(String username){
        authoritiesRepository.deleteById(username);
        return authoritiesList();
    }

    public List<Authorities> updateAuth (Authorities auth){
        authoritiesRepository.saveAndFlush(auth);
        return authoritiesList();
    }
    public Optional<Authorities> getAuthById(String id){

        return  authoritiesRepository.findById(id);
    }
}