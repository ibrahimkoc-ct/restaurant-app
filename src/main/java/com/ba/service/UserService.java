package com.ba.service;

import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.mapper.UserMapper;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String addUser(UserDTO userDTO){
        List<Role> roleList= new ArrayList<>();
        for (int i=0; i<userDTO.getRoles().size(); i++){
            Role role=roleRepository.findById(userDTO.getRoles().get(i).getId()).get();
            roleList.add(role);
        }
        userDTO.getRoles().removeAll(userDTO.getRoles());
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.getRoles().addAll(roleList);
        userRepository.save(user);
        return "kisi eklendi";
    }
    public String deleteUser(Long id){
    User user =userRepository.findById(id).get();
    List<Role> roleList=user.getRoles();
    for(int i=0; i<roleList.size(); i++){
        Role role = user.getRoles().get(i);
        user.getRoles().remove(role);
        if(user.getRoles().size()>0){
            roleRepository.save(user.getRoles().get(i));
        }  }
        userRepository.deleteById(id);
        return "kisi silindi";
    }
    public UserDTO updateUser(UserDTO dto){
        userRepository.saveAndFlush(UserMapper.INSTANCE.toEntity(dto));
        return dto;
    }
    public UserDTO getUserById(Long id){
       User user= userRepository.findById(id).get();
       return UserMapper.INSTANCE.toDTO(user);
    }
    public List<UserDTO> getAllUser(){
        List<User> user=userRepository.findAll();
        return UserMapper.INSTANCE.toDTOList(user);
    }

}
