package com.ba.controller;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import com.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    UserService service;

    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO userDto){
        service.addUser(userDto);
        return "kisi eklendi";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        service.deleteUser(id);
      return "kisi silindi";
    }
    @GetMapping("/list")
    public List<UserDTO> getAllUser(){
     return service.getAllUser();
    }
    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return service.updateUser(userDTO);
    }
    @GetMapping("/id/{id}")
    public UserDTO getUserById(@PathVariable Long id){
      return service.getUserById(id);

    }
    @GetMapping("/admin-login")
    public String loginAdminCheck(){
        return "giris basarılı";
    }
    @GetMapping("/user-login")
    public String loginUserCheck(){
        return "giris basarılı";
    }




}


