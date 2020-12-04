package com.ba.service;


import com.ba.converter.UsersDtoConverter;
import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

//    public void loadAdmin(Users users){
//        usersRepository.save(users);
//    }

    public List<UsersDTO> listUsers(){
        List<Users> userList = usersRepository.findAll();
        return UsersDtoConverter.userListToUserDTOList(userList);
    }

    public String addUsers(UsersDTO usersDTO){

        usersRepository.save(UsersDtoConverter.usersDTOaddUsers(usersDTO));
        return "kisi eklendi";
    }

    public String deleteUsers(String username){

        usersRepository.deleteById(UsersDtoConverter.userDTOdeleteUsers(username));
        return "kisi silindi";
    }

    public UsersDTO updateUsers (UsersDTO usersDTO){

        usersRepository.saveAndFlush(UsersDtoConverter.usersDTOaddUsers(usersDTO));
        return usersDTO;
    }
    public UsersDTO getUsersById(String id){
        Optional<Users> dtoList =usersRepository.findById(id);
        return UsersDtoConverter.userDTOgetByID(dtoList);

    }

}

