package com.ba.controller;

import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;
import com.ba.entity.Users;
import com.ba.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthoritiesController {

    @Autowired
    AuthoritiesService authoritiesService;

    @DeleteMapping("/delete/{username}")
    public void deleteAuth(@PathVariable String username){
         authoritiesService.deleteAuth(username);
    }
    @PutMapping("/update")
    public void updateAuth(@RequestBody AuthoritiesDTO auth){ authoritiesService.updateAuth(auth);
    }

    @GetMapping("/id/{id}")
    public AuthoritiesDTO AuthById(@PathVariable String id){
        return authoritiesService.getAuthById(id);
    }

    @GetMapping("/listall")
    public List<AuthoritiesDTO> authoritiesList(){
        return authoritiesService.authoritiesList();
    }

    @PostMapping("/add")
    public void addAuth(@RequestBody AuthoritiesDTO authDto){
        authoritiesService.addAuth(authDto);
    }
}