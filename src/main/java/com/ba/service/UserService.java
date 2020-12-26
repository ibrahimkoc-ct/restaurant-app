package com.ba.service;

import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.BussinessRuleException;
import com.ba.exception.SystemException;
import com.ba.mapper.UserMapper;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String addUser(UserDTO userDTO){
        List<Role> roleList= new ArrayList<>();
        userDTO.getRoles().forEach(role->roleList.add(roleRepository.findById(role.getId()).get()));
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoles(roleList);
        userRepository.save(UserMapper.INSTANCE.toEntity(userDTO));
        return "kisi eklendi";
    }
    public String deleteUser(Long id){
        Optional<User> optionalUser =userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new BussinessRuleException("User not found in database");
        }
        User user=optionalUser.get();
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
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()){
           throw new BussinessRuleException("User not found in database");
       }
       return UserMapper.INSTANCE.toDTO(user.get());
    }
    public List<UserDTO> getAllUser(){
        try{
            List<User> user=userRepository.findAll();
            return UserMapper.INSTANCE.toDTOList(user);
        }
        catch (Exception e){
            throw new SystemException("System Failed!");
        }

    }

}
