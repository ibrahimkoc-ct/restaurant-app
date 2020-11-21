package com.ba.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hi")
public class HelloController {
    @GetMapping("/user")
    public String sayHelloForUser(){
        return "Hi from controller as User";
    }
    @GetMapping("/admin")
    public String sayHelloForAdmin(){
        return "Hi from contoller as Admin";
    }

}
