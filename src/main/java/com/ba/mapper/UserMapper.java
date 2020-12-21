package com.ba.mapper;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
    List<User> toEntityList(List<UserDTO> dtoList);
    List<UserDTO> toDTOList(List<User> userList);

}
