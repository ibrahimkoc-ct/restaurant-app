package com.ba.controller;

import com.ba.dto.UserDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    UserService service;

    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO userDto) {
        if (userDto == null || userDto.getId() != null) {
            throw new BussinessRuleException("User cannot be empty!");
        }
        return service.addUser(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        service.deleteUser(id);
        return "kisi silindi";
    }

    @GetMapping("/list")
    public List<UserDTO> getAllUser() {
        return service.getAllUser();
    }

    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == null) {
            throw new BussinessRuleException("User cannot be empty!");
        }
        return service.updateUser(userDTO);
    }

    @GetMapping("/id/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        return service.getUserById(id);

    }

    @GetMapping("/admin-login")
    public String loginAdminCheck() {
        return "giris basarılı";
    }




    @GetMapping("/user-login")
    public String loginUserCheck() {
        return "giris basarılı";
    }
}


