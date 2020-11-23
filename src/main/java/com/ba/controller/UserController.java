package com.ba.controller;


import com.ba.entity.ProductSales;
import com.ba.entity.User;
import com.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userepository;

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user) {
        userepository.addUser(user);
        return userepository.getAllUser();
    }
    @GetMapping("/list")
    public List<User> getAllUser() {

       return userepository.getAllUser();
    }
    @DeleteMapping("/delete/{id}")
    public List<User> deleteUser(@PathVariable Long id) {
         userepository.deleteUser(id);
        return userepository.getAllUser();
    }
    @PutMapping("/update/{id}")
    public List<User>updateUser(@PathVariable long id, @RequestBody User user) {

       return userepository.updateUser(id,user);
    }
    @GetMapping("/id/{id}")
    public Optional<User> getUsertById(@PathVariable long id){
        return userepository.getUserById(id);

    }
}
