package com.ba.converter;

import com.ba.dto.RoleDTO;
import com.ba.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDTOConverter {
    public static Role roleDTOtoRole(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    public static RoleDTO roleToRoleDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    public static List<Role> roleDTOListToRoleList(List<RoleDTO> dtoList) {
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            Role role = new Role();
            role.setName(dtoList.get(i).getName());
            role.setId(dtoList.get(i).getId());
            list.add(role);
        }
        return list;

    }
    public static List<RoleDTO> roleListToRoleDTOList(List<Role> roleList) {
        List<RoleDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < roleList.size(); i++) {
            RoleDTO dto = new RoleDTO();
            dto.setName(roleList.get(i).getName());
            dto.setId(roleList.get(i).getId());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public static RoleDTO getRoleById(Optional<Role> list) {
        RoleDTO dto = new RoleDTO();
        dto.setId(list.get().getId());
        dto.setName(list.get().getName());
        return dto;
    }
}
