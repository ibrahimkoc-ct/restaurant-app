package com.ba.service;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.exception.BussinessRuleException;
import com.ba.exception.SystemException;
import com.ba.mapper.RoleMapper;
import com.ba.mapper.UserMapper;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String addUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return "kisi eklendi";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "kisi silindi";
    }

    public UserDTO updateUser(UserDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        if (optionalUser.isEmpty()) {
            throw new SystemException("User not found");
        }
        if (!optionalUser.get().getEmail().equals(dto.getEmail())) {
            optionalUser.get().setEmail(dto.getEmail());
        }
        if (!optionalUser.get().getUsername().equals(dto.getUsername())) {
            optionalUser.get().setUsername(dto.getUsername());
        }
        if (!optionalUser.get().getPassword().equals(dto.getPassword())) {
            optionalUser.get().setPassword(encoder.encode(dto.getPassword()));
        }
        if (optionalUser.isEmpty() && !dto.getRoles().isEmpty()) {
            optionalUser.get().setRoles(RoleMapper.INSTANCE.toEntityList(dto.getRoles()));
        }
        //buraya devam et
        userRepository.saveAndFlush(UserMapper.INSTANCE.toEntity(dto));
        return dto;
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BussinessRuleException("User not found in database");
        }
        return UserMapper.INSTANCE.toDTO(user.get());
    }

    public List<UserDTO> getAllUser() {
        List<User> user = userRepository.findAll();
        if(user.isEmpty()){
            throw new SystemException("User not found");
        }
        return UserMapper.INSTANCE.toDTOList(user);
    }

}
