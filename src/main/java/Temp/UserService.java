//package com.ba.service;
//import com.ba.entity.User;
//import com.ba.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//
//    @Autowired
//    UserRepository repository;
//
//
//
//    public List<User> getAllUser() {
//        repository.findAll();
//        return repository.findAll();
//    }
//    public List<User> deleteUser(Long id) {
//        repository.deleteById(id);
//        return repository.findAll();
//    }
//    public List<User> addUser(User user) {
//
//
//        repository.save(user);
//        return repository.findAll();
//
//
//    }
//    public List<User> updateUser(long id, User user) {
//        Optional<User> optionalUser = repository.findAll().stream().filter(user1 -> user1.getId() == id).findAny();
//        if (optionalUser == null) {
//            System.out.println("girilen ID ile haber bulunamadı!");
//
//        }
//        optionalUser.get().setUsername(user.getUsername());
//        optionalUser.get().setPassword(user.getPassword());
//        optionalUser.get().setRole(user.getRole());
//        repository.saveAndFlush(optionalUser.get());
//
//        return repository.findAll();
//
//    }
//
//    public Optional<User>  getUserById(Long id){
//
//     return  repository.findById(id);
//    }
//
//}
