package com.ba.mapper;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO dto);
    List<Role> toEntityList(List<RoleDTO> dtoList);
    List<RoleDTO> toDTOList(List<Role> roles);
}
