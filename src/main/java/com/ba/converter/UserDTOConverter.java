package com.ba.converter;


import com.ba.dto.UserDTO;
import com.ba.entity.Category;
import com.ba.entity.Role;
import com.ba.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDTOConverter {
    private static final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

    public static User userDTOtoUser(UserDTO userDTO){
    User user= new User();
    user.setId(userDTO.getId());
    user.setPassword(encoder.encode(userDTO.getPassword()));
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setEnabled(userDTO.isEnabled());
    user.setRoles(RoleDTOConverter.roleDTOListToRoleList(userDTO.getRoles()));
    return user;
    }

    public static UserDTO userToUserDTO(User user){
    UserDTO dto= new UserDTO();
    dto.setEmail(user.getEmail());
    dto.setEnabled(user.isEnabled());
    dto.setId(user.getId());
    dto.setPassword(user.getPassword());
    dto.setUsername(user.getUsername());
    dto.setRoles(RoleDTOConverter.roleListToRoleDTOList(user.getRoles()));
        return dto;
    }

    public static List<User> userDTOLisTOUserList(List<UserDTO> userDTO){
        List<User> userList = new ArrayList<>();
        for (int i=0; i<userDTO.size(); i++){
            User user= new User();
            user.setPassword(userDTO.get(i).getPassword());
            user.setUsername(userDTO.get(i).getUsername());
            user.setEmail(userDTO.get(i).getEmail());
            user.setEnabled(userDTO.get(i).isEnabled());
            user.setRoles(RoleDTOConverter.roleDTOListToRoleList(userDTO.get(i).getRoles()));
            userList.add(user);
        }
        return userList;
    }
    public static List<UserDTO> userListToUserDTOList(List<User> user){
        List<UserDTO> dtoList= new ArrayList<>();
        for(int i=0; i<user.size(); i++){
            UserDTO dto= new UserDTO();
            dto.setEmail(user.get(i).getEmail());
            dto.setEnabled(user.get(i).isEnabled());
            dto.setId(user.get(i).getId());
            dto.setPassword(user.get(i).getPassword());
            dto.setUsername(user.get(i).getUsername());
            dto.setRoles(RoleDTOConverter.roleListToRoleDTOList(user.get(i).getRoles()));
            dtoList.add(dto);
        }
        return dtoList;
    }
    public static User addUserIdtoDto(List<Role> roleList,UserDTO dto){
        User user= new User();
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEnabled(dto.isEnabled());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setId(dto.getId());
//        user.setRoles(RoleDTOConverter.roleDTOListToRoleList(dto.getRoles()));
        user.getRoles().addAll(roleList);
        return user;


    }

}
