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
    public void addUsers(@RequestBody UsersDTO users){
        usersService.addUsers(users);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUsers(@PathVariable String username){
        usersService.deleteUsers(username);
    }

    @PutMapping("/update")
    public void updateUsers(@RequestBody UsersDTO users){
         usersService.updateUsers(users);
    }
    @GetMapping("/id/{id}")
    public UsersDTO getUsersById(@PathVariable String id){
        return usersService.getUsersById(id);

    }
}