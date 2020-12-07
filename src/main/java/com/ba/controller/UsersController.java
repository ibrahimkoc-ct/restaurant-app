package com.ba.controller;

import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/listall")
    public List<UsersDTO> listUsers(){
        return usersService.listUsers();
    }

    @PostMapping("/add")
    public String addUsers(@RequestBody UsersDTO users){
        usersService.addUsers(users);
        return "kisi eklendi";
    }

    @DeleteMapping("/delete/{username}")
    public String deleteUsers(@PathVariable String username){
        usersService.deleteUsers(username);
        return "kisi silindi";
    }

    @PutMapping("/update")
    public UsersDTO updateUsers(@RequestBody UsersDTO users){
         usersService.updateUsers(users);
         return users;
    }
    @GetMapping("/id/{id}")
    public UsersDTO getUsersById(@PathVariable String id){
        return usersService.getUsersById(id);

    }
}